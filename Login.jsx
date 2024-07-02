import React, { useState } from 'react';
import styles from './Login.module.css';
import { Link } from 'react-router-dom';

const Login = () => {
  const [signUpMode, setSignUpMode] = useState(false);
  const [formValues, setFormValues] = useState({
    nomecompleto: '',
    nomeocial: '',
    datadenascimento: '',
    email: '',
    senha: '',
    confsenha: ''
  });

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFormValues({
      ...formValues,
      [name]: value
    });
  };

  const handleSignUp = async (e) => {
    e.preventDefault();

    if (formValues.senha !== formValues.confsenha) {
      alert('As senhas não coincidem!');
      return;
    }

    const usuario = {
      nomecompleto: formValues.nomecompleto,
      nomeocial: formValues.nomeocial,
      datadenascimento: formValues.datadenascimento,
      email: formValues.email,
      senha: formValues.senha
    };

    try {
      const response = await fetch('http://localhost:8082/usuario/registrar', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(usuario)
      });

      if (response.ok) {
        alert('Conta criada com sucesso!');
      } else {
        alert('Erro ao criar conta!');
      }
    } catch (error) {
      console.error('Erro:', error);
      alert('Erro ao criar conta!');
    }
  };

  const handleLogin = async (e) => {
    e.preventDefault();

    const usuarioLogin = {
      email: formValues.email,
      senha: formValues.senha
    };

    try {
      const response = await fetch('http://localhost:8082/usuario/login', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(usuarioLogin)
      });

      if (response.ok) {
        alert('Login realizado com sucesso!');
      } else {
        const errorText = await response.text();
        alert('Erro ao fazer login: ' + errorText);
      }
    } catch (error) {
      console.error('Erro:', error);
      alert('Erro ao fazer login!');
    }
  };

  return (
    <div className={styles.backgroundPacesLogin}>
      <div className={`${styles.containerLogin} ${signUpMode ? styles.signUpModeLogin : ''}`}>
        <div className={styles.formsContainerLogin}>
          <div className={styles.signinSignupLogin}>
            <form className={styles.signInFormLogin} onSubmit={handleLogin}>
              <h2 className={styles.titleLogin}>Entrar</h2>
              <div className={styles.inputFieldLogin}>
                <i className="fas fa-user"></i>
                <input
                  type="text"
                  name="email"
                  placeholder="Email"
                  value={formValues.email}
                  onChange={handleInputChange}
                />
              </div>
              <div className={styles.inputFieldLogin}>
                <i className="fas fa-lock"></i>
                <input
                  type="password"
                  name="senha"
                  placeholder="Senha"
                  value={formValues.senha}
                  onChange={handleInputChange}
                />
              </div>
              <button className={styles.btnLogin} type="submit">Login</button>
            </form>
            <form className={styles.signUpFormLogin} onSubmit={handleSignUp}>
              <h2 className={styles.titleLogin}>Registre-se</h2>
              <div className={styles.inputFieldLogin}>
                <i className="fas fa-user"></i>
                <input
                  type="text"
                  name="nomecompleto"
                  placeholder="Nome Completo"
                  value={formValues.nomecompleto}
                  onChange={handleInputChange}
                />
              </div>
              <div className={styles.inputFieldLogin}>
                <i className="fas fa-user"></i>
                <input
                  type="text"
                  name="nomeocial"
                  placeholder="Nome Social (opcional)"
                  value={formValues.nomeocial}
                  onChange={handleInputChange}
                />
              </div>
              <div className={styles.inputFieldLogin}>
                <i className="fas fa-lock"></i>
                <input
                  type="date"
                  name="datadenascimento"
                  placeholder="Data de Nascimento"
                  value={formValues.datadenascimento}
                  onChange={handleInputChange}
                />
              </div>
              <div className={styles.inputFieldLogin}>
                <i className="fas fa-envelope"></i>
                <input
                  type="text"
                  name="email"
                  placeholder="Seu melhor email"
                  value={formValues.email}
                  onChange={handleInputChange}
                />
              </div>
              <div className={styles.inputFieldLogin}>
                <i className="fas fa-lock"></i>
                <input
                  type="password"
                  name="senha"
                  placeholder="Senha"
                  value={formValues.senha}
                  onChange={handleInputChange}
                />
              </div>
              <div className={styles.inputFieldLogin}>
                <i className="fas fa-lock"></i>
                <input
                  type="password"
                  name="confsenha"
                  placeholder="Confirme sua senha"
                  value={formValues.confsenha}
                  onChange={handleInputChange}
                />
              </div>
              <button className={styles.btnLogin} type="submit">Registre-se</button>
            </form>
          </div>
        </div>
        <div className={styles.panelsContainerLogin}>
          <div className={`${styles.panelLogin} ${styles.leftPanelLogin}`}>
            <div className={styles.panelContentLogin}>
              <h3>Novo por aqui?</h3>
              <p>Vamos criar uma nova conta agora mesmo!</p>
              <button
                className={`${styles.btnLogin} ${styles.btnTransparentLogin}`}
                onClick={() => setSignUpMode(true)}
              >
                Registrar-se
              </button>
            </div>
          </div>
          <div className={`${styles.panelLogin} ${styles.rightPanelLogin}`}>
            <div className={styles.panelContentLogin}>
              <h3>Já tem uma conta?</h3>
              <p>Entre por aqui!</p>
              <button
                className={`${styles.btnLogin} ${styles.btnTransparentLogin}`}
                onClick={() => setSignUpMode(false)}
              >
                Entrar
              </button>
              <Link to="/">Voltar</Link>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Login;
