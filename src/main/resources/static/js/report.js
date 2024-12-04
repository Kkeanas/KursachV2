function filterById(jsonObject, id) {return jsonObject.filter(function(jsonObject) {return (jsonObject['id'] == id);})[0];}
async function fetchReport() {
    try {
        let id = document.URL.substring(document.URL.lastIndexOf("/")+1)
        const response = await fetch(`http://localhost:8080/api/report/${id}`);
        const data = await response.json();
        return data; // Теперь это вернет данные из функции fetchGroups
    } catch (error) {
        console.error(error);
    }
}
async function fetchOrganizations() {
    try {
        const response = await fetch(`http://localhost:8080/api/organization`);
        const data = await response.json();
        return data; // Теперь это вернет данные из функции fetchGroups
    } catch (error) {
        console.error(error);
    }
}

function fillform() {
    report = fetchReport().then(
        data => {
            console.log(data)
            const studentArray = ["stuFio","stuRegAddress","stuPhone","stuEmail","stuComment","stuCommentFromStudent"]
            for (let i of studentArray) {
                if (i == "stuFio") {
                    document.getElementById(i).value = `${data.student.stuFirstName} ${data.student.stuSecondName} ${data.student.stuSurName}`
                    continue
                }
                if (data.student[i] == null) {
                    continue
                }

                document.getElementById(i).value = data.student[i]
            }
            fetchOrganizations().then(org=>{
                var organiz = org;
                option = document.createElement("option")
                option.value = "choose"
                option.innerText = "Выбрать"
                document.getElementById("orgName").appendChild(option)

                option = document.createElement("option")
                option.value = "add"
                option.innerText = "Добавить"
                document.getElementById("orgName").appendChild(option)


                for (let i of org) {
                    option = document.createElement("option")
                    option.setAttribute("id",`org-${i["id"]}`)
                    option.value = i["id"]
                    option.innerText = i["orgName"]
                    document.getElementById("orgName").appendChild(option)
                }
            })
            const orgArray = ["orgName","orgAddress","orgPhone","orgEmail","orgContactName","orgRequirements"]
            for (let i of orgArray) {
                if (data.organization == null) {
                    break;
                }
                console.log(document.getElementById(i))
                document.getElementById(i).value = data.organization[i]
            }
            const prcArray = ["prcRelevance","prcMaterial","prcDescription","prcDirector","prcStarting","prcEnding","prcNextYear","prcReportPassed","prcPosition","prcWork", "prcIsPayed","prcFutureWork"]
            for (let i of prcArray) {
                if (data.practice[i] == null) {
                    continue
                }
                let el = document.getElementById(i)
                if ( el.type == "select-one") {
                    el.value = data.practice[i];
                }
                else if (el.type == "date") {
                    b = new Date(Date.parse(data.practice[i])).toISOString().split('T')[0]
                    el.value = b;
                } else {
                    el.value = data.practice[i]
                }
            }
        }
    )
}
fillform()


function organizationChanged(id) {
    if (id != "add" && id != "choose"){
        fetchOrganizations().then(org => {
            console.log(id)
            choosed = filterById(org, id)
            console.log(choosed)
            const orgArray = ["orgAddress", "orgPhone", "orgEmail", "orgContactName", "orgRequirements"]
            for (let i of orgArray) {
                document.getElementById(i).value = choosed[i]
            }
        })
    } else if (id == "add") {
        showAddOrganizationModal()
    }
}

function  showAddOrganizationModal() {
    document.getElementById("add-orgmodal").style.display = "block"
}

function submitStudent() {
    inputsStu = document.querySelectorAll("#student .form-group input, #student .form-group textarea")
    inputsPra = document.querySelectorAll("#practice .form-group select, #practice .form-group input")
    formData = {}
    formData.student = {}
    formData.practice = {}
    for (i of inputsStu) {
        formData.student[i.id] = i.value
    }
    for (i of inputsPra) {
        formData.practice[i.id] = i.value
    }
    formData["id"] = document.URL.substring(document.URL.lastIndexOf("/")+1)
    formData["organization_id"] = document.getElementById("orgName").value
    if( formData.organization_id !== "choose" && formData.organization_id !== "add") {
        fetchReportUPDATE(formData)
    }
}

function addOrg(form) {
    formData = {}
    inputs = form.querySelectorAll("input")
    inputs.forEach( input=> {
            formData[input.id.substring(input.id.lastIndexOf("-")+1)] = input.value
        }
    )
    fetchOrganizationPost(formData)

}

async function fetchOrganizationPost(formData) {
    try {
        console.log(JSON.stringify(formData))
        const response = await fetch('http://localhost:8080/api/organization', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(formData)
        })
        if (response.ok) {
            showNewOrganization(response.json())
            document.getElementById("add-orgmodal").style.display = "none"
        }
        else {
            showNewUserError()
        }
    } catch (error) {
        console.error(error);
    }
}

function showNewOrganization(promise) {
    promise.then(data=>{
        option = document.createElement("option")
        option.setAttribute("id",`org-${data["id"]}`)
        option.value = data["id"]
        option.innerText = data["orgName"]
        document.getElementById("orgName").appendChild(option)
    })
}

async function fetchReportUPDATE(formData) {
    try {
        console.log(JSON.stringify(formData))
        const response = await fetch('http://localhost:8080/api/report', {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(formData)
        })
        if (response.ok) {
            console.log("ok")
        }
        else {
            console.log("ne ok")
        }
    } catch (error) {
        console.error(error);
        console.log(123)
    }
}