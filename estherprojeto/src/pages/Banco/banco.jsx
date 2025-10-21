import React, { useState, useEffect } from "react";
import { marmitaService } from "../../services/api";


function Banco() {

    const [vmarmitas, setMarmitas] = useState([]);

    useEffect(() => {
        marmitaService.listarDisponiveis()
            .then((marmitas) => {
                setMarmitas(marmitas);
                console.log(marmitas);
            })
            .catch(err => console.error("Erro ao buscar marmitas", err));
    }, []);

    return (

        <div className="app-container">
            <div className="main-content">
                Cardápio de Marmitas
            </div>


            <div className="cards-container">
                {vmarmitas.map((marmita) => (
                    <div key={marmita.id} className="produto-card">
                        {marmita.imagemUrl ? (
                            <img 
                                src={marmita.imagemUrl} 
                                alt={marmita.nome} 
                                className="produto-imagem"
                                onError={(e) => {
                                    e.target.style.display = 'none';
                                    e.target.nextSibling.style.display = 'flex';
                                }}
                            />
                        ) : null}
                        <div className="produto-imagem-placeholder" style={{display: marmita.imagemUrl ? 'none' : 'flex'}}>
                            <span>🍱</span>
                        </div>
                        <h3>{marmita.nome}</h3>
                        <p className="descricao">{marmita.descricao}</p>
                        <p className="preco">R$ {marmita.preco?.toFixed(2)}</p>
                        <p className="categoria">Categoria: {marmita.categoriaNome}</p>
                        <p className="tempo">Tempo: {marmita.tempoPreparoMinutos} min</p>
                    </div>
                ))}
            </div>


        </div>

    );
}
export default Banco;
