<template>
  <div class="register-container">
    <h2>Cadastro</h2>

    <form @submit.prevent="handleRegister">
      <input type="text" placeholder="Nome" v-model="form.name" required />
      <input type="email" placeholder="Email" v-model="form.email" required />
      <input type="password" placeholder="Senha" v-model="form.password" required />

      <button type="submit">Cadastrar</button>
    </form>

    <p v-if="success" class="success">{{ success }}</p>
    <p v-if="error" class="error">{{ error }}</p>
  </div>
</template>

<script>
import api from '../services/api';

export default {
  name: 'Register',
  data() {
    return {
      form: {
        name: '',
        email: '',
        password: ''
      },
      success: null,
      error: null
    };
  },
  methods: {
    async handleRegister() {
      this.success = null;
      this.error = null;

      try {
        await api.post('/auth/register', this.form);

        this.success = 'Usuário cadastrado com sucesso!';
        this.form = { name: '', email: '', password: '' };
      } catch (err) {
        if (err.response && err.response.data) {
          this.error = err.response.data.message || 'Erro ao cadastrar usuário';
        } else {
          this.error = 'Erro de conexão com o servidor';
        }
      }
    }
  }
};
</script>
