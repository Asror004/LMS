function facultyList() {
    let table = document.getElementById("table");
    let btn = document.getElementById("btn");
    let nav = document.getElementById("nav");

    btn.setAttribute('disabled', 'disabled');

    fetch('/getFacultyList?pg=0')
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

                nav.innerHTML = `
                        <li class="page-item">
                            <a class="page-link" th:if="${data.prev}" th:href="@{'?pg='+${data.current - 1}}" aria-label="Previous">
                                <span aria-hidden="true">&laquo;</span>
                            </a>
                        </li>`;

                for (let i = 0; i < data.pages; i++) {
                    nav.insertAdjacentHTML('beforeend',
                                `<li class="page-item">
                                        <a class="page-link" href="?&pg=${i}">${i + 1}</a>
                                    </li>`);
                }
/*
                let li = document.createElement('<li class="page-item"></li>');

                li.innerHTML = `
                            <a class="page-link" th:if="${data.next}" th:href="@{'&pg='+${data.current + 1}}" aria-label="Next">
                                <span aria-hidden="true">&raquo;</span>
                            </a>
                `
                nav.appendChild(li);*/

            }
        })
        .catch(error => console.error(error));
}

function selectFaculty(name) {
    document.getElementById('facultyDiv').innerHTML = `
            <label for="faculty" class="form-label">Faculty</label>
            <input type="text" disabled class="form-control" style="cursor: auto" name="faculty"
           aria-describedby="basic-addon1" id="faculty" value="${name}">
        `;
    document.getElementById('table').remove();
}

