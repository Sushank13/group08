import logo from './logo.svg';
import './App.css';
import axios from 'axios';
import React, { useState } from 'react';

axios.defaults.baseURL = 'https://sample-boot-service.onrender.com';
axios.defaults.headers.common['Content-Type'] = 'application/json;charset=utf-8';
axios.defaults.headers.common['Access-Control-Allow-Origin'] = '*';

function App() {
	const [responseData, setResponseData] = useState(null);

const handleButtonClick = () => {
    axios.get('/welcome')
      .then(response => {
        console.log(response);
        setResponseData(response.data);
      })
      .catch(error => {
        console.log(error);
      });
  };

  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" height="100" />
        
        <button style={{ padding: '32px 16px' }} onClick={handleButtonClick}>Test Welcome API</button>
        {responseData && <div>{responseData.message}</div>}
      </header>
    </div>
  );
}

export default App;
