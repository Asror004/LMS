if (window.performance){
    sessionStorage.removeItem("firstName");
    sessionStorage.removeItem("name");
}
function facultyList(pg) {
    let table = document.getElementById("table");
    let btn = document.getElementById("btn-f");
    let nav = document.getElementById("nav");

    table.innerHTML = '';
    nav.innerHTML = '';

    btn.setAttribute('disabled', 'disabled');

    fetch('/getFacultyList?pg=' + pg)
        .then(response => response.json())
        .then(data => {

            if (data.content.length === 0) {
                table.innerHTML = `<span> Fakuletlar yo'q </span>`
            } else {
                let thead = document.createElement('thead');
                thead.innerHTML = `
                        <tr>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Tanlash</th>
                         </tr>
                    `
                table.appendChild(thead);

                let tbody = document.createElement('tbody');

                data.content.forEach(faculty => {
                    let tr = document.createElement('tr');
                    tr.innerHTML = `
                            <td> ${faculty.id} </td>
                            <td> ${faculty.name} </td>
                            <td>
                            <button onclick="selectFaculty('${faculty.name}')" class="btn btn-primary">
                            Tanlash</button> </td>
                        `
                    tbody.appendChild(tr);
                });

                table.appendChild(tbody);

                pagination(data, nav, "faculty")
            }
        })
        .catch(error => console.error(error));
}

function selectFaculty(name) {
    document.getElementById('facultyDiv').innerHTML = `
            <label for="faculty" class="form-label">Faculty</label>
            <input type="text" readonly class="form-control" style="cursor: auto" name="faculty"
           aria-describedby="basic-addon1" id="faculty" value="${name}">
        `;
    document.getElementById('table').innerHTML = '';
    document.getElementById('nav').innerHTML = '';

    document.getElementById('groupDiv').style.display = 'block';
}


function groupList(pg) {
    let table = document.getElementById("table");
    let btn = document.getElementById("btn-g");
    let nav = document.getElementById("nav");
    let faculty = document.getElementById("faculty");
    document.getElementById("facultyDiv").style.display = 'none';

    table.innerHTML = '';
    nav.innerHTML = '';

    btn.setAttribute('disabled', 'disabled');

    fetch('/getGroupList?fc=' + faculty.value + '&pg=' + pg)
        .then(response => response.json())
        .then(data => {

            if (data.content.length === 0) {
                table.innerHTML = `<span>Bu fakultetda grupalar yo'q </span>`
            } else {
                let thead = document.createElement('thead');
                thead.innerHTML = `
                        <tr>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Tanlash</th>
                         </tr>
                    `
                table.appendChild(thead);

                let tbody = document.createElement('tbody');

                data.content.forEach(group => {
                    let tr = document.createElement('tr');
                    tr.innerHTML = `
                            <td> ${group.id} </td>
                            <td> ${group.name} </td>
                            <td>
                            <button onclick="selectGroup('${group.name}')" class="btn btn-primary">
                            Tanlash</button> </td>
                        `
                    tbody.appendChild(tr);
                });

                table.appendChild(tbody);

                pagination(data, nav, "group");
            }
        })
        .catch(error => console.error(error));
}

function selectGroup(name) {
    document.getElementById('groupDiv').innerHTML = `
            <label for="group" class="form-label">Group</label>
            <input type="text" readonly class="form-control" style="cursor: auto" name="group"
           aria-describedby="basic-addon1" id="group" value="${name}">
        `;

    document.getElementById('table').innerHTML = '';

    document.getElementById('nav').innerHTML = '';

    document.getElementById("facultyDiv").style.display = 'block';
    document.getElementById('groupDiv').style.display = 'block';
    document.getElementById('dayDiv').style.display = 'block';
}

function week() {
    let table = document.getElementById("table");
    let btn = document.getElementById("btn-d");
    let nav = document.getElementById("nav");
    let group = document.getElementById("group");

    document.getElementById("facultyDiv").style.display = 'none';
    document.getElementById("groupDiv").style.display = 'none';

    table.innerHTML = '';
    nav.innerHTML = '';

    table.className = "table table-bordered week mt-3";

    btn.setAttribute('disabled', 'disabled');

    fetch('/week?gr=' + group.value)
        .then(response => response.json())
        .then(data => {
            let thead = document.createElement('thead');
            thead.innerHTML = `
                        <tr>
                            <th>Para</th>
                            <th>Monday</th>
                            <th>Tuesday</th>
                            <th>Wednesday</th>
                            <th>Thursday</th>
                            <th>Friday</th>
                            <th>Saturday</th>
                            <th>Sunday</th>
                        </tr>
                    `
            table.appendChild(thead);

            let tbody = document.createElement('tbody');


            for (let i = 1; i <= 6; i++) {
                let tr = document.createElement('tr');

                tr.innerHTML = `
                    <td>${i}</td>
                `;

                for (let j = 0; j < 7; j++) {

                    let hLesson = hasLesson(data, i, j);

                    tr.insertAdjacentHTML("beforeend", `
                        <td class="${hLesson ? 'bg-danger text-white' : 'bg-success text-white'}" 
                        style="${hLesson ? '' : 'cursor : pointer;'}"> 
                            ${hLesson ? 'Dars bor' :
                        "Tanlash <input type='hidden' value='" + i + "' name='para'>" +
                        "<input type='hidden' value='" + j + "' name='day'> "}
                        </td>
                    `)
                }

                tbody.appendChild(tr);
            }

            table.appendChild(tbody);

            let week2 = document.querySelector(".week");
            week2.addEventListener('click', (event) => {
                let target = event.target;
                if (target.querySelector('input')) {
                    let para = target.querySelector('input:nth-child(1)');
                    let day = target.querySelector('input:nth-child(2)');

                    selectTime(day.value, para.value);
                }
            });

        })
        .catch(error => console.error(error));
}

function selectTime(day, para) {
    document.getElementById('dayDiv').innerHTML = `
            <label for="group" class="form-label">Time</label>
            <input type="text" readonly class="form-control" style="cursor: auto"
           aria-describedby="basic-addon1" id="time" value="Haftaning ${parseInt(day) + 1}-kuni ${para}-para">
           <input type="hidden" value="${day}" name="day">
           <input type="hidden" value="${para}" name="para">
        `;

    let table = document.getElementById('table');
    table.className = "table table-striped mt-5";
    table.innerHTML = ``;

    document.getElementById("facultyDiv").style.display = 'block';
    document.getElementById('groupDiv').style.display = 'block';
    document.getElementById('dayDiv').style.display = 'block';
    document.getElementById('teacherDiv').style.display = 'block';
}

function teacherList(pg) {
    document.getElementById("facultyDiv").style.display = 'none';
    document.getElementById("groupDiv").style.display = 'none';
    document.getElementById("dayDiv").style.display = 'none';

    let teacherDiv = document.getElementById("teacherDiv");

    let firstName = null;
    let input = document.getElementById("teacherId");

    if (input) {
        firstName = input.value;
        sessionStorage.setItem("firstName", firstName);
    }

    let table = document.getElementById("table");
    let nav = document.getElementById("nav");

    table.innerHTML = '';
    nav.innerHTML = '';
    teacherDiv.innerHTML = '';

    teacherDiv.insertAdjacentHTML("beforeend", `
        <button class="btn btn-primary w-100" disabled onclick="selectTeacher(0)" id="btn-t">O'qituvchi tanlash</button>
        <label for="teacherId"  class="form-label mt-4">Search teacher</label> 
        <div class="input-group mb-3">
            <input type="text" class="form-control" placeholder="Firstname" id="teacherId">  
            <button class="btn btn-outline-secondary" type="button" id="button-addon2" 
            onclick="${'teacherList(0)'}">Search</button>
        </div>         
    `);

    let firstNameInput = document.getElementById("teacherId");
    if (firstNameInput && sessionStorage.getItem("firstName")){
        firstNameInput.value = sessionStorage.getItem("firstName");
    }

    let day = document.getElementsByName("day").item(0).value;
    let para = document.getElementsByName("para").item(0).value;

    let request = '/getTeacherList?pg=' + pg + "&day=" + day + "&para=" + para;

    if (firstName) {
        request += "&firstName=" + firstName;
    }

    fetch(request)
        .then(response => response.json())
        .then(data => {

            if (data.content.length === 0) {
                table.innerHTML = `<span> Teacherlar yo'q </span>`
            } else {
                let thead = document.createElement('thead');
                thead.innerHTML = `
                        <tr>
                            <th>ID</th>
                            <th>Firstname</th>
                            <th>Subject</th>
                            <th>Tanlash</th>
                         </tr>
                    `
                table.appendChild(thead);

                let tbody = document.createElement('tbody');
                let i = 1;

                data.content.forEach(teacher => {
                    let tr = document.createElement('tr');
                    tr.innerHTML = `
                            <td> ${i++} </td>
                            <td> ${teacher.firstName} </td>
                            <td> ${teacher.subject} </td>
                            <td>
                            <button onclick="selectTeacher('${teacher.firstName}')" class="btn btn-primary">
                            Tanlash </button> </td>
                        `
                    tbody.appendChild(tr);
                });

                table.appendChild(tbody);

                pagination(data, nav, "teacher");
            }
        })
        .catch(error => console.error(error));
}

function selectTeacher(name) {
    document.getElementById('teacherDiv').innerHTML = `
            <label class="form-label">Teacher</label>
            <input type="text" readonly class="form-control" style="cursor: auto" name="teacher"
           aria-describedby="basic-addon1" id="teacher" value="${name}">
        `;

    document.getElementById('table').innerHTML = ``;
    document.getElementById('nav').innerHTML = ``;

    document.getElementById("facultyDiv").style.display = 'block';
    document.getElementById('groupDiv').style.display = 'block';
    document.getElementById('dayDiv').style.display = 'block';
    document.getElementById('teacherDiv').style.display = 'block';
    document.getElementById('roomDiv').style.display = 'block';
}

function roomList(pg) {
    document.getElementById("facultyDiv").style.display = 'none';
    document.getElementById("groupDiv").style.display = 'none';
    document.getElementById("dayDiv").style.display = 'none';
    document.getElementById("teacherDiv").style.display = 'none';

    let teacherDiv = document.getElementById("roomDiv");

    let name = null;
    let input = document.getElementById("roomId");

    if (input) {
        name = input.value;
        sessionStorage.setItem("name", name);
    }

    let table = document.getElementById("table");
    let nav = document.getElementById("nav");

    table.innerHTML = '';
    nav.innerHTML = '';
    teacherDiv.innerHTML = '';

    teacherDiv.insertAdjacentHTML("beforeend", `
        <button class="btn btn-primary w-100" disabled onclick="selectTeacher(0)" id="btn-r">Xonani tanlash</button>
        <label for="roomId"  class="form-label mt-4">Search Room</label> 
        <div class="input-group mb-3">
            <input type="text" class="form-control" placeholder="Name" id="roomId">  
            <button class="btn btn-outline-secondary" type="button" id="button-addon2" 
            onclick="${'roomList(0)'}">Search</button>
        </div>         
    `);

    let nameInput = document.getElementById("roomId");
    if (nameInput && sessionStorage.getItem("name")){
        nameInput.value = sessionStorage.getItem("name");
    }

    let day = document.getElementsByName("day").item(0).value;
    let para = document.getElementsByName("para").item(0).value;

    let request = '/getRoomList?pg=' + pg + "&day=" + day + "&para=" + para;

    if (name) {
        request += "&name=" + name;
    }

    fetch(request)
        .then(response => response.json())
        .then(data => {

            if (data.content.length === 0) {
                table.innerHTML = `<span> Roomlar yo'q </span>`
            } else {
                let thead = document.createElement('thead');
                thead.innerHTML = `
                        <tr>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Tanlash</th>
                         </tr>
                    `
                table.appendChild(thead);

                let tbody = document.createElement('tbody');
                let i = 1;

                data.content.forEach(room => {
                    let tr = document.createElement('tr');
                    tr.innerHTML = `
                            <td> ${i++} </td>
                            <td> ${room.name} </td>
                            <td>
                            <button onclick="selectRoom('${room.name}')" class="btn btn-primary">
                            Tanlash </button> </td>
                        `
                    tbody.appendChild(tr);
                });

                table.appendChild(tbody);

                pagination(data, nav, "room");
            }
        })
        .catch(error => console.error(error));
}

function selectRoom(name) {
    document.getElementById('roomDiv').innerHTML = `
            <label class="form-label">Room</label>
            <input type="text" readonly class="form-control" style="cursor: auto" name="room"
           aria-describedby="basic-addon1" id="room" value="${name}">
        `;

    document.getElementById('table').innerHTML = ``;
    document.getElementById('nav').innerHTML = ``;

    document.getElementById("facultyDiv").style.display = 'block';
    document.getElementById('groupDiv').style.display = 'block';
    document.getElementById('dayDiv').style.display = 'block';
    document.getElementById('teacherDiv').style.display = 'block';
    document.getElementById('button').style.display = 'block';
}

function hasLesson(data, para, day) {

    let hLesson = false;

    data.forEach(lesson => {
        if (lesson.dayOfWeek === day && lesson.para === para) {
            hLesson = true;
        }
    });

    return hLesson;
}

function pagination(data, nav, functionName) {


    if (data.prev) {
        nav.innerHTML = `
                        <li class="page-item" onclick="${functionName}List(${data.current - 1})">
                                <a class="page-link" style="cursor: pointer">
                                    <span aria-hidden="true">&laquo;</span>
                                </a>
                        </li>`;
    }

    for (let i = 0; i < data.pages; i++) {

        nav.insertAdjacentHTML('beforeend', `<li class="page-item" onclick="${functionName}List(${i})">
                                        <a class="page-link ${(i === data.current ? 'active' : '')}" style="cursor: pointer">${i + 1}</a>
                                    </li>`);

    }

    if (data.next) {
        nav.insertAdjacentHTML('beforeend', `
                            <li class="page-item" onclick="${functionName}List(${data.current + 1})" aria-label="Next">
                                <a class="page-link" style="cursor: pointer">
                                    <span aria-hidden="true">&raquo;</span>
                                </a>
                            </li>
                `);
    }

}


if (sessionStorage.getItem("save")){
    document.getElementById("added").innerHTML = `
            <div class="sc">Successfully added</div>
            <div class="bg"></div>
        `;
    sessionStorage.removeItem("save");
}


let element = document.getElementById("otherElement");

if (element) {
    element.href = "/lesson/create";
    sessionStorage.setItem("save", true);
    element.click();
}


