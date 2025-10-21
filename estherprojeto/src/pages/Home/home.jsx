import { Link } from 'react-router-dom'
import '../Home/style1.css';
import backgroundImage from '../img/back.jpg';

import React from "react";


function Home (){

    return (
      <div
      className="home-container"
      style={{
        backgroundImage: `url(${backgroundImage})`,
        backgroundSize: 'cover',
        backgroundPosition: 'center',
        backgroundRepeat: 'no-repeat'
      }}
    >
      <h1></h1>
    </div>
      );
   

      

}
export default Home;