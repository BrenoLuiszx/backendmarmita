//npx json-server --watch db.json --port 3000
//npm run dev

import React, { useState, useEffect } from "react";
import { marmitaService, categoriaService, cozinheiroService } from "../../services/api";

const Marmitas = () => {
  const [vnome, setNome] = useState('');
  const [vdesc, setDesc] = useState('');
  const [vpreco, setPreco] = useState('');
  const [vtempo, setTempo] = useState('');
  const [vcategoriaId, setCategoriaId] = useState('');
  const [vcozinheiroId, setCozinheiroId] = useState('');

  const [vmarmita, setMarmita] = useState([]);
  const [categorias, setCategorias] = useState([]);
  const [cozinheiros, setCozinheiros] = useState([]);
  const [message, setMessage] = useState('');

  // Busca os dados ao carregar a tela
  useEffect(() => {
    carregarDados();
  }, []);

  const carregarDados = async () => {
    try {
      const [marmitas, categoriasData, cozinheirosData] = await Promise.all([
        marmitaService.listarDisponiveis(),
        categoriaService.listarTodas(),
        cozinheiroService.listarTodos()
      ]);
      
      setMarmita(marmitas);
      setCategorias(categoriasData);
      setCozinheiros(cozinheirosData);
      
      // Define valores padrão
      if (categoriasData.length > 0) setCategoriaId(categoriasData[0].id);
      if (cozinheirosData.length > 0) setCozinheiroId(cozinheirosData[0].id);
    } catch (error) {
      console.error("Erro ao carregar dados:", error);
      setMessage("Erro ao carregar dados");
    }
  };

  // Função handleSubmit
  const handleSubmit = async (e) => {
    e.preventDefault();
    
    try {
      const novaMarmita = {
        nome: vnome,
        descricao: vdesc,
        preco: parseFloat(vpreco),
        categoriaId: parseInt(vcategoriaId),
        cozinheiroId: parseInt(vcozinheiroId),
        tempoPreparoMinutos: parseInt(vtempo)
      };
      
      await marmitaService.criar(novaMarmita);
      setMessage("Marmita cadastrada com sucesso!");
      
      // Limpa formulário
      setNome('');
      setDesc('');
      setPreco('');
      setTempo('');
      
      // Recarrega lista
      const marmitas = await marmitaService.listarDisponiveis();
      setMarmita(marmitas);
    } catch (error) {
      setMessage(typeof error === 'string' ? error : "Erro ao cadastrar marmita");
    }
  };

  const handleDelete = async (id) => {
    if (window.confirm("Tem certeza que deseja deletar esta marmita?")) {
      try {
        await marmitaService.deletar(id);
        setMessage("Marmita deletada com sucesso!");
        const marmitas = await marmitaService.listarDisponiveis();
        setMarmita(marmitas);
      } catch (error) {
        setMessage("Erro ao deletar marmita");
      }
    }
  };

  return (
    <div className="app-container">
      <div className="main-content">
        Cadastro de Marmitas
      </div>

      <form onSubmit={handleSubmit}>
        <div className="form-group">
          <label htmlFor="nome">Nome do Marmita</label>
          <input
            id="nome"
            type="text"
            placeholder="Nome do Produto"
            required
            value={vnome}
            onChange={(e) => setNome(e.target.value)}
          />
        </div>

        <div className="form-group">
          <label htmlFor="descricao">Descrição</label>
          <input
            id="descricao"
            type="text"
            placeholder="Descrição da Marmita"
            value={vdesc}
            onChange={(e) => setDesc(e.target.value)}
          />
        </div>

        <div className="form-group">
          <label htmlFor="tempo">Tempo de Preparo (minutos)</label>
          <input
            id="tempo"
            type="number"
            placeholder="Ex: 30"
            required
            value={vtempo}
            onChange={(e) => setTempo(e.target.value)}
          />
        </div>

        <div className="form-group">
          <label htmlFor="preco">Preço</label>
          <input
            id="preco"
            type="text"
            placeholder="Preço de Venda"
            value={vpreco}
            onChange={(e) => setPreco(e.target.value)}
          />
        </div>

        <div className='form-group'>
          <label htmlFor="categoria">Categoria:</label>
          <select
            id="categoria"
            value={vcategoriaId}
            onChange={(e) => setCategoriaId(e.target.value)}
            required
          >
            <option value="">Selecione uma categoria</option>
            {categorias.map(categoria => (
              <option key={categoria.id} value={categoria.id}>
                {categoria.nome}
              </option>
            ))}
          </select>
        </div>

        <div className='form-group'>
          <label htmlFor="cozinheiro">Cozinheiro:</label>
          <select
            id="cozinheiro"
            value={vcozinheiroId}
            onChange={(e) => setCozinheiroId(e.target.value)}
            required
          >
            <option value="">Selecione um cozinheiro</option>
            {cozinheiros.map(cozinheiro => (
              <option key={cozinheiro.id} value={cozinheiro.id}>
                {cozinheiro.nome}
              </option>
            ))}
          </select>
        </div>

 

        <div className="form-group">
          <button type="submit">Cadastrar Marmita</button>
        </div>
      </form>

      {message && <p>{message}</p>}

      <div className="main-content">
        Marmitas Cadastradas
      </div>

      <ul>
        {vmarmita.map(marmita => (
          <li key={marmita.id}>
            <strong>{marmita.nome}</strong> - R$ {marmita.preco}<br/>
            Categoria: {marmita.categoriaNome} | Cozinheiro: {marmita.cozinheiroNome}<br/>
            Tempo: {marmita.tempoPreparoMinutos} min
            <div>
              <button onClick={() => handleDelete(marmita.id)}>Deletar</button>
            </div>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default Marmitas;
