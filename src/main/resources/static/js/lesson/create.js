function facultyList(){
    let table = document.getElementById("table");
    let btn = document.getElementById("btn");
    btn.setAttribute('disabled', 'disabled');

    fetch('/getFacultyList?pg=0')
        .then(response => response.json())
        .then(data => {

            if (data.length === 0) {
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

                console.log(data.totalPages);

                // data.forEach(faculty => {
                //     let tr = document.createElement('tr');
                //     tr.innerHTML = `
                //             <td> ${faculty.id} </td>
                //             <td> ${faculty.name} </td>
                //             <td>
                //             <button onclick="selectFaculty('${faculty.name}')" class="btn btn-primary">
                //             Tanlash</button> </td>
                //         `
                //     tbody.appendChild(tr);
                // })
                // table.appendChild(tbody);


            }
        })
        .catch(error => console.error(error));
}

function selectFaculty(name){
    document.getElementById('facultyDiv').innerHTML = `
            <label for="faculty" class="form-label">Faculty</label>
            <input type="text" disabled class="form-control" style="cursor: auto" name="faculty"
           aria-describedby="basic-addon1" id="faculty" value="${name}">
        `;
    document.getElementById('table').remove();
}

