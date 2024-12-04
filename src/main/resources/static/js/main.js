// Все запросы

async function fetchGroupsGET() {
    try {
        const response = await fetch("http://localhost:8080/api/group");
        return await response.json();
    } catch (error) {
        console.error(error);
    }
}

async function fetchGroupPOST(formData) {
    try {
        console.log(JSON.stringify(formData))
        const response = await fetch('http://localhost:8080/api/group', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(formData)
        })
        if (response.ok) {
            showNewGroup(response.json())
        }
        else {
            showNewGroupError(response.json())
        }
    } catch (error) {
        console.error(error);
        console.log(123)
    }
}

async function fetchReportPOST(formData) {
    try {
        console.log(JSON.stringify(formData))
        const response = await fetch('http://localhost:8080/api/report', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(formData)
        })
        if (response.ok) {
            showNewUser(response.json())
        }
        else {
            showNewUserError(response.json())
        }
    } catch (error) {
        console.error(error);
        console.log(123)
    }
}

async function fetchReportDELETE(id) {
    try {
        const response = await fetch(`http://localhost:8080/api/report/${id}`,{
            method: 'DELETE'
        });
        if (response.ok) {
            delstuul(id)
        }
    } catch (error) {
        console.error(error);
    }
}
function delStudent(id) {
    let delid = id.substring(id.lastIndexOf("-")+1)
    fetchReportDELETE(delid)
}

// все что связано с фронтом
function showAddGroupModal() {
    document.getElementById("modalWindowGroup").style.display = "block"
}

function delstuul(id) {
    document.getElementById(`report-${id}`).remove()

}

function createGroup() {
    formData = {}
    s = document.getElementById("groupName").value
    formData["grpName"] = s
    fetchGroupPOST(formData)
}

function concealError() {
    console.log(123)
    let errors = document.querySelectorAll("#addForm .error")
    for (let i = 0; i< errors.length; i++) {
        errors[i].style.display = "none"
    }
}

function showNewUserError(response) {
    let errors = document.querySelectorAll("#addForm .error")
    for (let i = 0; i< errors.length; i++) {
        errors[i].style.display = "block"
    }


}

function showNewGroupError(response) {
    let errors = document.querySelectorAll("#addFormGroup .error")
    for (let i = 0; i< errors.length; i++) {
        errors[i].style.display = "block"
    }


}

function concealErrorGroup() {
    console.log(123)
    let errors = document.querySelectorAll("#addFormGroup .error")
    for (let i = 0; i< errors.length; i++) {
        errors[i].style.display = "none"
    }
}



function showCreateModal(id) {
    document.getElementById("addForm").lastElementChild.id = id
    document.getElementById("modalWindow").style.display = "block"

}

function createReport(id) {
    console.log(id)
    formData = {}
    s = document.getElementById("studentName").value.split(" ")
    formData["group_id"] = id.substring(id.lastIndexOf("-")+1)
    formData["stuFirstName"] = s[0]
    formData["stuSecondName"] = s[1]
    formData["stuSurName"] = s[2]
    fetchReportPOST(formData)
}

function showNewGroup(promise) {
    promise.then(data=>{
        console.log(data)
        let groupDiv = document.createElement("div")
        groupDiv.setAttribute("id",`grp-${data.id}`)
        groupDiv.setAttribute("class","group-container")

        let groupH4 = document.createElement("h4")
        groupH4.setAttribute("class","group-title")
        groupH4.innerText = data.grpName

        let groupUl = document.createElement("ul")
        groupUl.setAttribute("id",`groupl-${data.id}`)
        groupUl.setAttribute("class","group-list")

        let addButton = document.createElement("button")
        addButton.setAttribute("id",`add-${data.id}`)
        addButton.setAttribute("class","add-btn")
        addButton.setAttribute("onclick","showCreateModal(this.id)")
        addButton.innerText = "Добавить студента в группу"
        let wrapLi = document.createElement("li")

        wrapLi.appendChild(addButton)
        groupUl.appendChild(wrapLi)
        groupDiv.appendChild(groupH4)
        groupDiv.appendChild(groupUl)
        document.getElementById("groups-container").insertBefore(groupDiv,document.getElementById("groups-container").lastChild)


    })
}


function showNewUser(promise) {
    promise.then(data =>
    {
        console.log(data)
        let groupUl = document.getElementById(`groupl-${data.group_id}`)

        let studentLi = document.createElement("li")
        studentLi.setAttribute("class",`stu-str`)
        studentLi.setAttribute("id",`report-${data.id}`)

        let studentA = document.createElement("a")
        studentA.setAttribute("id",`stu-${data.id}`)
        studentA.setAttribute("href",`http://localhost:8080/report/${data.id}`)
        studentA.innerText = `${data.student.stuFirstName} ${data.student.stuSecondName} ${data.student.stuSurName}`

        let delButton = document.createElement("button")
        delButton.setAttribute("onclick",'delStudent(this.id)')
        delButton.setAttribute('id',`del-${data.id}`)
        delButton.setAttribute("class","del-stu-btn")

        let i = document.createElement("i")
        i.setAttribute("style","font-size:15px;color:red")
        i.setAttribute("class","material-icons")
        i.innerText = "delete"

        delButton.appendChild(i)
        studentLi.appendChild(studentA)
        studentLi.appendChild(delButton)
        console.log(studentLi)
        console.log(groupUl)
        console.log(groupUl.lastChild)
        groupUl.insertBefore(studentLi,groupUl.lastChild)
    })
}


function createGroupsList() {
    fetchGroupsGET().then(data => {
        let groups = data
        groups.forEach(group=>{
            let groupDiv = document.createElement("div")
            groupDiv.setAttribute("id",`grp-${group.id}`)
            groupDiv.setAttribute("class","group-container")

            let groupH4 = document.createElement("h4")
            groupH4.setAttribute("class","group-title")
            groupH4.innerText = group.grpName

            let groupUl = document.createElement("ul")
            groupUl.setAttribute("id",`groupl-${group.id}`)
            groupUl.setAttribute("class","group-list")

            let addButton = document.createElement("button")
            addButton.setAttribute("id",`add-${group.id}`)
            addButton.setAttribute("class","add-btn")
            addButton.setAttribute("onclick","showCreateModal(this.id)")
            addButton.innerText = "Добавить студента в группу"
            let wrapLi = document.createElement("li")

            group.reports.forEach(report=>{
                if (report.organization == null) {
                    console.log("null")
                }
                let studentLi = document.createElement("li")
                studentLi.setAttribute("class",`stu-str`)
                studentLi.setAttribute("id",`report-${report.id}`)

                let studentA = document.createElement("a")
                studentA.setAttribute("id",`stu-${report.id}`)
                studentA.setAttribute("href",`http://localhost:8080/report/${report.id}`)
                studentA.innerText = `${report.student.stuFirstName} ${report.student.stuSecondName} ${report.student.stuSurName}`

                let delButton = document.createElement("button")
                delButton.setAttribute("onclick",'delStudent(this.id)')
                delButton.setAttribute('id',`del-${report.id}`)
                delButton.setAttribute("class","del-stu-btn")

                let i = document.createElement("i")
                i.setAttribute("style","font-size:15px;color:red")
                i.setAttribute("class","material-icons")
                i.innerText = "delete"


                delButton.appendChild(i)
                studentLi.appendChild(studentA)
                studentLi.appendChild(delButton)
                groupUl.appendChild(studentLi)
            })
            wrapLi.appendChild(addButton)
            groupUl.appendChild(wrapLi)
            groupDiv.appendChild(groupH4)
            groupDiv.appendChild(groupUl)
            document.getElementById("groups-container").appendChild(groupDiv)
        })
        let addGroupButton = document.createElement("button")
        addGroupButton.setAttribute("id",`addGroup`)
        addGroupButton.setAttribute("class","add-btn")
        addGroupButton.setAttribute("onclick","showAddGroupModal()")
        addGroupButton.innerText = "Добавить группу"
        document.getElementById("groups-container").appendChild(addGroupButton)
    });

}
createGroupsList()

