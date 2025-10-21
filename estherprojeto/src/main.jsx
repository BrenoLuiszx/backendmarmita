import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './global.css'
import Rotas from './rotas.jsx'
import { AuthProvider } from './context/AuthContext.jsx'

createRoot(document.getElementById('root')).render(
  <StrictMode>
    <AuthProvider>
      <Rotas />
    </AuthProvider>
  </StrictMode>,
)
