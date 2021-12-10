import logo from "./logo.svg";
import "./App.css";
import React, { useState, useEffect } from "react";
import axios from "axios";

function App() {
  const [people, setPeople] = useState([]);

  useEffect(() => {
    fetchPeople(setPeople);
  }, []);

  function fetchPeople(setter) {
    axios
      .get("http://localhost:8080/person")
      .then((result) => setter(result.data));
    // fetch("http://localhost:8080/person", {
    //   method: "GET",
    //   mode: "no-cors",
    // }).then(res => setter(res.data))
    // }).then(result => setter([{ id: 1, firstName: "Chad", lastName: "Champion" }]))
  }

  function deletePerson(id) {
    axios.delete("http://localhost:8080/person/" + id).then(() => {
      window.location.reload();
    });
  }

  function createPerson(firstName, lastName, email) {
    axios
      .post(
        "http://localhost:8080/person",
        JSON.stringify({
          firstName: firstName,
          lastName: lastName,
          email: email,
        }),
        { headers: { "Content-Type": "application/json" } }
      )
      .then((response) => console.log(response))
      .then(() => {
        window.location.reload();
      });
  }

  function editPerson(id, firstName, lastName, email) {
    axios
      .patch(
        "http://localhost:8080/person",
        { id: id },
        JSON.stringify({
          firstName: firstName,
          lastName: lastName,
          email: email,
        }),
        { headers: { "Content-Type": "application/json" } }
      )
      .then((response) => console.log(response));
  }

  return (
    <div className="App">
      <table>
        <tr>
          <td>Vorname</td>
          <td>Nachname</td>
          <td>Email</td>
          <td></td>
        </tr>
        {people &&
          people.map((person) => (
            <tr>
              <td>{person.firstName}</td>
              <td>{person.lastName}</td>
              <td>{person.email}</td>
              <td>
                <button
                  onClick={() => {
                    deletePerson(person.id);
                  }}
                >
                  Delete
                </button>
              </td>
            </tr>
          ))}
      </table>
      <button
        onClick={() => {
          createPerson("New", "User", "new@user.com");
        }}
      >
        add User
      </button>
    </div>
  );
}

export default App;
