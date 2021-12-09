import logo from './logo.svg';
import './App.css';
import React, { useState } from 'react';


function App() {
  const [people, setPeople] = useState(0);
  
  return (
    <div className="App">
      <table>
        {getPeople().map(person => {
          return 
          <tr>
            <td>{person.firstName}</td>
            <td>{person.lastName}</td>
            <td>
              <button onClick={deletePerson(person.id)}>Delete</button>
            </td>
          </tr>;
        })}
      </table>
      <button onClick={() =>{
        //console.log("Test")
        createPerson("New", "User", "new@user.com");
        //setPeople(getPeople())
      }}>
        add User
        </button>
    </div>
  );
}

function getPeople() {
  fetch('http://localhost:8080/person', {
    method: 'GET',
    mode: 'no-cors'
  })
  return [{id: 1,firstName: "Chad",lastName: "Champion"}];
}

function deletePerson(id) {
  fetch('http://localhost:8080/person' + id, 
  {
    method: 'DELETE',
    mode: 'no-cors'
  })
}

function createPerson(firstName, lastName, email) {
  console.log(JSON.stringify({
    firstName: firstName,
    lastName: lastName,
    email: email
  }));
  fetch('http://localhost:8080/person', {
    method: 'POST',
    mode: 'no-cors',
    body: JSON.stringify({
      firstName: firstName,
      lastName: lastName,
      email: email
    }),
    headers: {
      Accept: 'application/json'
    }
  })
}

function editPerson(id, firstName, lastName, email) {
  fetch('http://localhost:8080/person', {
    method: 'PATCH',
    mode: 'no-cors',
    body: JSON.stringify({
      id: id,
      firstName: firstName,
      lastName: lastName,
      email: email
    }),
    headers: {
      Accept: 'application/json'
    }
  })
}

export default App;
