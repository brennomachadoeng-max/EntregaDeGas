import React, { createContext, useState, useContext, useEffect } from 'react';
import { LoginResponseDTO } from '../../pages/login/types';
import { obterUsuario, removerUsuario } from '../../service/authStorage';

interface AuthContextData {
  user: LoginResponseDTO | null;
  setUser: React.Dispatch<React.SetStateAction<LoginResponseDTO | null>>;
  loadingContext: boolean;
  logout: () => Promise<void>;
}

const AuthContext = createContext<AuthContextData>({} as AuthContextData);

export function AuthProvider({ children }: { children: React.ReactNode }) {
  const [user, setUser] = useState<LoginResponseDTO | null>(null);
  const [loadingContext, setLoadingContext] = useState(true);

  useEffect(() => {
  async function carregarDadosIniciais() {
    try {
      const usuarioSalvo = await obterUsuario();
      if (usuarioSalvo) {
        setUser(usuarioSalvo);
      }
    } catch (error) {
      console.error("Erro ao carregar usuário do storage:", error);
    } finally {
      setLoadingContext(false);
    }
  }
  carregarDadosIniciais();
}, []);

  async function logout() {
    await removerUsuario(); 
    setUser(null);
  }

  return (
    <AuthContext.Provider value={{ user, setUser, loadingContext, logout }}>
      {children}
    </AuthContext.Provider>
  );
}

export function useAuthContext() {
  const context = useContext(AuthContext);
  if (!context) {
    throw new Error('useAuthContext deve ser utilizado dentro de um AuthProvider');
  }
  return context;
}

