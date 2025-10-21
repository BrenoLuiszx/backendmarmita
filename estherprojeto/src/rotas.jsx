//npm install react-router-dom
import { BrowserRouter, Routes, Route, Outlet } from "react-router-dom";
import Header from './pages/Header/header';
import Home from './pages/Home/home';
import Produto from './pages/Produto/produto';
 
import Formulario from './pages/Formulario/formulario';
import Banco from './pages/Banco/banco'
 
import Login from './pages/Login/login';
import Usuario from './pages/Usuario/usuario';
import OLayout from './pages/Usuario/usariolayo';
 
function RoutesApp (){
 
    return(
          <BrowserRouter>
                <Routes>
                    <Route path="/" element={<Login/>}/>
                    <Route path="/login" element={<Login/>}/>
                    <Route path="/usuario" element={<Usuario/>}/>
 
 
                    <Route element={<Layout/>}>
 
                            <Route path="/home" element={<Home/>}/>
                            <Route path="/produto" element={<Produto/>}/>
                            <Route path="/formulario" element={<Formulario/>}/>
                       <Route path="/banco" element={<Banco/>}/>
                       <Route path="/perfil" element={<OLayout/>}/>
                           
                    </Route>  
                </Routes>
           </BrowserRouter>  
 
    );    
   
}
 
function Layout(){
         return(
            <>
                <Header /> {/*Exibe o cabecalho*/}
             
                <main>
                    <Outlet /> {/*Corpo da página*/}
                </main>
            </>
         )
}
 
export default RoutesApp