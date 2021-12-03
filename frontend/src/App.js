import logo from './logo.svg';
import './App.css';

function App() {
  return (
    <div className="App">
      <table>
        {getPeople().map(person => {
          return <tr><td>{person.firstName}</td><td>{person.lastName}</td></tr>
        })}
      </table>
    </div>
  );
}

function getPeople() {
  return [{id: 1,firstName: "Chad",lastName: "Champion"}];
}

export default App;
