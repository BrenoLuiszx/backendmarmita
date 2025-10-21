import { Link } from 'react-router-dom'
import { useAuth } from '../../context/AuthContext'
import Logo from'../img/nutribox.jpg'

function Header(){
    const { usuario, logout, isAuthenticated } = useAuth();

    const handleLogout = () => {
        logout();
        window.location.href = '/login';
    };

    return(
        <header>
            <div>
              <div>
               <img src={Logo} alt="Logo" title="Logo Loja" />
            </div>
             
            </div>
            <nav>
                <a href="/home" className="abas">Home</a>
                <span className="separador">|</span>

                <a href="/produto" className="abas">Produto</a>
                <span className="separador">|</span>

                 <a href="/banco" className="abas">Marmitas</a>
                <span className="separador">|</span>

                <a href="/perfil" className="abas">Perfil</a>
                <span className="separador">|</span>

                {isAuthenticated() ? (
                    <>
                        <span className="usuario-info">Olá, {usuario?.nome}</span>
                        <button onClick={handleLogout} className="abas">Sair</button>
                    </>
                ) : (
                    <a href="/login" className="abas">Login</a>
                )}
            </nav>
        </header>
    );
}
export default Header;