import axios from 'axios';

const API_BASE_URL = 'http://localhost:8080/api';

const api = axios.create({
  baseURL: API_BASE_URL,
  headers: {
    'Content-Type': 'application/json',
  },
});

export const authService = {
  login: async (email, senha) => {
    try {
      const response = await api.post('/auth/login', { email, senha });
      return response.data;
    } catch (error) {
      throw error.response?.data || 'Erro no login';
    }
  },

  register: async (usuario) => {
    try {
      const response = await api.post('/auth/register', usuario);
      return response.data;
    } catch (error) {
      throw error.response?.data || 'Erro no cadastro';
    }
  }
};

export const marmitaService = {
  listarTodas: async () => {
    try {
      const response = await api.get('/marmitas');
      return response.data;
    } catch (error) {
      throw error.response?.data || 'Erro ao buscar marmitas';
    }
  },

  listarDisponiveis: async () => {
    try {
      const response = await api.get('/marmitas/disponiveis');
      return response.data;
    } catch (error) {
      throw error.response?.data || 'Erro ao buscar marmitas disponíveis';
    }
  },

  criar: async (marmita) => {
    try {
      const response = await api.post('/marmitas', marmita);
      return response.data;
    } catch (error) {
      throw error.response?.data || 'Erro ao criar marmita';
    }
  },

  atualizar: async (id, marmita) => {
    try {
      const response = await api.put(`/marmitas/${id}`, marmita);
      return response.data;
    } catch (error) {
      throw error.response?.data || 'Erro ao atualizar marmita';
    }
  },

  deletar: async (id) => {
    try {
      await api.delete(`/marmitas/${id}`);
      return true;
    } catch (error) {
      throw error.response?.data || 'Erro ao deletar marmita';
    }
  }
};

export const categoriaService = {
  listarTodas: async () => {
    try {
      const response = await api.get('/categorias');
      return response.data;
    } catch (error) {
      throw error.response?.data || 'Erro ao buscar categorias';
    }
  }
};

export const cozinheiroService = {
  listarTodos: async () => {
    try {
      const response = await api.get('/cozinheiros');
      return response.data;
    } catch (error) {
      throw error.response?.data || 'Erro ao buscar cozinheiros';
    }
  }
};

export const usuarioService = {
  listarTodos: async () => {
    try {
      const response = await api.get('/usuarios');
      return response.data;
    } catch (error) {
      throw error.response?.data || 'Erro ao buscar usuários';
    }
  },

  buscarPorId: async (id) => {
    try {
      const response = await api.get(`/usuarios/${id}`);
      return response.data;
    } catch (error) {
      throw error.response?.data || 'Erro ao buscar usuário';
    }
  },

  criar: async (usuario) => {
    try {
      const response = await api.post('/usuarios', usuario);
      return response.data;
    } catch (error) {
      throw error.response?.data || 'Erro ao criar usuário';
    }
  },

  atualizar: async (id, usuario) => {
    try {
      const response = await api.put(`/usuarios/${id}`, usuario);
      return response.data;
    } catch (error) {
      throw error.response?.data || 'Erro ao atualizar usuário';
    }
  },

  deletar: async (id) => {
    try {
      await api.delete(`/usuarios/${id}`);
      return true;
    } catch (error) {
      throw error.response?.data || 'Erro ao deletar usuário';
    }
  }
};

export default api;