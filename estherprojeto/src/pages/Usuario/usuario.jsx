//npm install react-hook-form
//npm install axios
//npm install json-server
//npm install -g json-server
 
//Comandos de inicialização
//npx json-server --watch db.json --port 3000
//npm run dev
 
import React, { useState, useEffect } from "react";
import { usuarioService, authService } from "../../services/api";
 
const Usuario = () => {
 
  // Estados para os campos
 
  const [nome, setNome] = useState("");
 
  const [email, setEmail] = useState("");
 
  const [senha, setSenha] = useState("");
 
  const [telefone, setTelefone] = useState("");
  const [endereco, setEndereco] = useState("");
  const [role, setRole] = useState("cliente");
  const [usuarios, setUsuarios] = useState([]);
  const [message, setMessage] = useState("");

  // Carrega usuários ao inicializar
  useEffect(() => {
    carregarUsuarios();
  }, []);

  const carregarUsuarios = async () => {
    try {
      const usuariosData = await usuarioService.listarTodos();
      setUsuarios(usuariosData);
    } catch (error) {
      console.error("Erro ao carregar usuários:", error);
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    
    try {
      const novoUsuario = {
        nome,
        email,
        senha,
        telefone,
        endereco,
        role
      };
      
      await authService.register(novoUsuario);
      setMessage("Usuário cadastrado com sucesso!");
      
      // Limpa os campos
      setNome("");
      setEmail("");
      setSenha("");
      setTelefone("");
      setEndereco("");
      setRole("cliente");
      
      // Recarrega a lista
      carregarUsuarios();
    } catch (error) {
      setMessage(typeof error === 'string' ? error : "Erro ao cadastrar usuário");
    }
  };

  const handleDelete = async (id) => {
    if (window.confirm("Tem certeza que deseja deletar este usuário?")) {
      try {
        await usuarioService.deletar(id);
        setMessage("Usuário deletado com sucesso!");
        carregarUsuarios();
      } catch (error) {
        setMessage("Erro ao deletar usuário");
      }
    }
  };
 
  return (
<div className="app-container">
<h2>Novo Cadastro</h2>
<form onSubmit={handleSubmit}>
<div className="form-group">
<label>Nome:</label>
<input
 
            type="text"
 
            value={nome}
 
            onChange={(e) => setNome(e.target.value)}
 
            required
 
          />
</div>
<div className="form-group">
<label>Email:</label>
<input
 
            type="email"
 
            value={email}
 
            onChange={(e) => setEmail(e.target.value)}
 
            required
 
          />
</div>
<div className="form-group">
<label>Senha:</label>
<input
 
            type="password"
 
            value={senha}
 
            onChange={(e) => setSenha(e.target.value)}
 
            required
 
          />
</div>
<div className="form-group">
<label>Telefone:</label>
<input
            type="text"
            value={telefone}
            onChange={(e) => setTelefone(e.target.value)}
          />
</div>
<div className="form-group">
<label>Endereço:</label>
<input
            type="text"
            value={endereco}
            onChange={(e) => setEndereco(e.target.value)}
          />
</div>
<div className="form-group">
<label>Tipo de Usuário:</label>
<select
            value={role}
            onChange={(e) => setRole(e.target.value)}
            required
>
<option value="cliente">Cliente</option>
<option value="admin">Admin</option>
</select>
</div> 
 
 
 
 
<button type="submit">Cadastrar</button>
</form>

{message && <p>{message}</p>}

<div className="usuarios-lista">
<h3>Usuários Cadastrados</h3>
<ul>
{usuarios.map(usuario => (
<li key={usuario.id}>
<strong>{usuario.nome}</strong> - {usuario.email} - {usuario.role}
<div>
<button onClick={() => handleDelete(usuario.id)}>Deletar</button>
</div>
</li>
))}
</ul>
</div>
</div>
 
  );
 
};
 
export default Usuario;