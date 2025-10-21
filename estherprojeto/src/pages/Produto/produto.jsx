import { useState, useEffect } from "react";
import React from "react";
import { categoriaService, marmitaService, cozinheiroService } from "../../services/api";

const Produto = () => {
  const [usuarios, setProduto] = useState([]);
  const [vcategoria, setCategoria] = useState([]);
  const [vcozinheiros, setCozinheiros] = useState([]);

  // ==================== CAMPOS DO FORMULÁRIO ====================
  const [vnome, setNome] = useState("");
  const [vdesc, setDesc] = useState("");
  const [vpreco, setPreco] = useState("");
  const [vimg, setImg] = useState("");
  const [vcategoriaId, setCategoriaId] = useState("");
  const [vcozinheiroId, setCozinheiroId] = useState("");
  const [vtempoPreparoMinutos, setTempoPreparoMinutos] = useState("");
  const [vimagemUrl, setImagemUrl] = useState("");

  // ==================== EDIÇÃO ====================
  const [isEditing, setIsEditing] = useState(false); // true se estiver editando
  const [editingId, setEditingId] = useState(null);  // id do produto que está editando

  // ==================== BUSCAR PRODUTOS ====================
  useEffect(() => {
    marmitaService.listarTodas()
      .then(res => setProduto(res))
      .catch(() => console.error("Erro ao buscar produtos"));
  }, []);

  // ==================== BUSCAR CATEGORIAS ====================
  useEffect(() => {
    categoriaService.listarTodas()
      .then(res => setCategoria(res))
      .catch(() => console.error("Erro ao buscar categorias"));
  }, []);

  // ==================== BUSCAR COZINHEIROS ====================
  useEffect(() => {
    cozinheiroService.listarTodos()
      .then(res => setCozinheiros(res))
      .catch(() => console.error("Erro ao buscar cozinheiros"));
  }, []);

  // ==================== CADASTRAR OU ATUALIZAR PRODUTO ====================
  const handleSubmit = async (e) => {
    e.preventDefault();

    const dataToSend = {
      nome: vnome,
      descricao: vdesc,
      preco: parseFloat(vpreco),
      categoriaId: parseInt(vcategoriaId),
      tempoPreparoMinutos: parseInt(vtempoPreparoMinutos) || 30,
      cozinheiroId: parseInt(vcozinheiroId),
      imagemUrl: vimagemUrl
    };

    try {
      console.log('Dados a enviar:', dataToSend);
      console.log('Editando?', isEditing, 'ID:', editingId);
      
      if (isEditing) {
        const result = await marmitaService.atualizar(editingId, dataToSend);
        console.log('Resultado da atualização:', result);
      } else {
        await marmitaService.criar(dataToSend);
      }

      // Atualiza a lista após cadastrar/editar
      const res = await marmitaService.listarTodas();
      setProduto(res);
      resetForm();
    } catch (err) {
      console.error("Erro ao salvar produto:", err);
      alert('Erro: ' + (err.message || err));
    }
  };

  // ==================== CARREGAR PRODUTO PARA EDIÇÃO ====================
  const handleEdit = (prod) => {
    setIsEditing(true);
    setEditingId(prod.id);
    setNome(prod.nome);
    setDesc(prod.descricao);
    setPreco(prod.preco);
    setImg(prod.imagem || "");
    setCategoriaId(prod.categoriaId || "");
    setCozinheiroId(prod.cozinheiroId || "");
    setTempoPreparoMinutos(prod.tempoPreparoMinutos || "");
    setImagemUrl(prod.imagemUrl || "");
  };

  // ==================== DELETAR PRODUTO ====================
  const handleDelete = async (id) => {
    try {
      await marmitaService.deletar(id);
      const res = await marmitaService.listarTodas();
      setProduto(res);
    } catch (err) {
      console.error("Erro ao deletar produto", err);
    }
  };

  // ==================== RESETAR FORMULÁRIO ====================
  const resetForm = () => {
    setIsEditing(false);
    setEditingId(null);
    setNome("");
    setDesc("");
    setPreco("");
    setImg("");
    setCategoriaId("");
    setCozinheiroId("");
    setTempoPreparoMinutos("");
    setImagemUrl("");
  };

  return (
    <div>
      <div className="app-container">
        <div className="main-content">Cadastro de Produto</div>

        <form onSubmit={handleSubmit}>
          <div className="form-group">
            <label>Nome</label>
            <input type="text" value={vnome} required onChange={(e) => setNome(e.target.value)} />
          </div>

          <div className="form-group">
            <label>Descrição</label>
            <input type="text" value={vdesc} onChange={(e) => setDesc(e.target.value)} />
          </div>

          <div className="form-group">
            <label>Preço</label>
            <input type="text" value={vpreco} onChange={(e) => setPreco(e.target.value)} />
          </div>

          <div className="form-group">
            <label>Categoria</label>
            <select value={vcategoriaId} required onChange={(e) => setCategoriaId(e.target.value)}>
              <option value="">Selecione a categoria</option>
              {vcategoria.map(cat => (
                <option key={cat.id} value={cat.id}>{cat.nome}</option>
              ))}
            </select>
          </div>

          <div className="form-group">
            <label>Cozinheiro</label>
            <select value={vcozinheiroId} required onChange={(e) => setCozinheiroId(e.target.value)}>
              <option value="">Selecione o cozinheiro</option>
              {vcozinheiros.map(coz => (
                <option key={coz.id} value={coz.id}>{coz.nome}</option>
              ))}
            </select>
          </div>

          <div className="form-group">
            <label>Tempo de Preparo (minutos)</label>
            <input 
              type="number" 
              value={vtempoPreparoMinutos} 
              min="1"
              onChange={(e) => setTempoPreparoMinutos(e.target.value)} 
            />
          </div>

          <div className="form-group">
            <label>URL da Imagem</label>
            <input
              type="url"
              value={vimagemUrl}
              placeholder="https://exemplo.com/imagem.jpg"
              onChange={(e) => setImagemUrl(e.target.value)}
            />
          </div>

<div className="form-group">
  <button type="submit">{isEditing ? "Atualizar" : "Cadastrar"}</button>
</div>

{isEditing && (
  <div className="form-group">
    <button type="button" onClick={resetForm}>Cancelar</button>
  </div>
)}
        </form>
      </div>

      <div className="app-container">
        <div className="main-content">Produtos Cadastrados</div>
        <ul>
          {usuarios.map(produto => (
            <li key={produto.id}>
              {produto.nome} - {produto.descricao} - R$ {produto.preco}
              <div>
                <button onClick={() => handleEdit(produto)}>Editar</button>
                <button onClick={() => handleDelete(produto.id)}>Deletar</button>
              </div>
            </li>
          ))}
        </ul>
      </div>
    </div>
  );
};

export default Produto; 