//npm install react-hook-form
//npm install axios
//npm install json-server
//npm install -g json-server
 
//Comandos de inicialização
//npx json-server --watch db.json --port 3000
//npm run dev
 
import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { authService } from "../../services/api";
import { useAuth } from "../../context/AuthContext";
 
const Login = () => {
   
    const [email, setEmail] = useState("");
    const [senha, setSenha] = useState("");
    const [message, setMessage] = useState("");
 
    const navigate = useNavigate();
    const { login } = useAuth();
 
    const handleLogin = async (e) => {
      e.preventDefault();
 
      try {
        const usuario = await authService.login(email, senha);
        setMessage("Login realizado com sucesso!");
        
        // Usa o contexto para fazer login
        login(usuario);
        
        // Redireciona para a Home
        navigate("/home");
      } catch (error) {
        setMessage(typeof error === 'string' ? error : "Email ou senha inválidos.");
      }
    };
 
 
    return(
        <div className="login-container">
            <div className="login-form">
                <h2>Login</h2>
 
                <form onSubmit={handleLogin}>
                    <div className="form-group">
                        <label>Email</label>
                        <input 
                            type="email" 
                            placeholder="Digite seu Email" 
                            value={email} 
                            required 
                            onChange={(e)=>setEmail(e.target.value)}
                        />
                    </div>
 
                    <div className="form-group">
                        <label>Senha</label>
                        <input 
                            type="password" 
                            placeholder="Digite sua Senha" 
                            value={senha} 
                            required 
                            onChange={(e)=>setSenha(e.target.value)} 
                        />
                    </div>
                           
                    <button type="submit">Entrar</button>
                </form>
 
                {message && <div className="message">{message}</div>}
 
                <div className="register-link">
                    <p>
                        Não tem uma conta? <a href="/usuario">Cadastre-se</a>
                    </p>
                </div>
            </div>
        </div>
 
    );
}
export default Login;
 