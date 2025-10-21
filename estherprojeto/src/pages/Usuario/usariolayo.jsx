import React, { useState, useEffect } from "react";
import { usuarioService } from "../../services/api";

function usuariolayout() {
  //Consulta os dados ao carregar a tela
  const [vproduto, setProduto] = useState([]); //Consulta os dados

  useEffect(() => {
    usuarioService.listarTodos()
      .then((usuarios) => {
        setProduto(usuarios);
        console.log(usuarios);
      })
      .catch((err) => console.error("Erro ao buscar usuários", err));
  }, []);

  return (
    <div className="app-container">
      <div className="main-content">Nutribox</div>
      <div className="cards-container">
        {vproduto.map((produto) => (
          <div key={produto.id} className="produto-card">
            {produto.imagem && (
              <img
                src={produto.imagem}
                alt={produto.nome}
                className="produto-imagem"
              />
            )}
            <h3>{produto.nome}</h3>
            <p>{produto.email}</p>
            <p>{produto.role}</p>
          </div>
        ))}
      </div>
    </div>
  );
}
export default usuariolayout;
