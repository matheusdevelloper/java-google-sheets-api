const form = document.getElementById('despesaForm');
const resumo = document.getElementById('resumo');
const message = document.getElementById('message');
const btnVoltar = document.getElementById('btnVoltar');
const btnEditar = document.getElementById('btnEditar');
const btnConfirmar = document.getElementById('btnConfirmar');
const toggleDarkMode = document.getElementById('toggleDarkMode');

let dadosParaEnviar = null;

const inputData = document.getElementById('data');
const hoje = new Date().toISOString().split('T')[0];
inputData.value = hoje;
inputData.setAttribute('min', hoje);

window.addEventListener('load', () => {
  document.getElementById('categoria').focus();
});

toggleDarkMode.addEventListener('click', () => {
  document.body.classList.toggle('dark-mode');
  toggleDarkMode.classList.toggle('fa-moon');
  toggleDarkMode.classList.toggle('fa-sun');
});

form.addEventListener('submit', (e) => {
  e.preventDefault();

  const formData = new FormData(form);
  const data = formData.get('data');
  const categoria = formData.get('categoria').trim();
  const descricao = formData.get('descricao').trim();
  const valor = parseFloat(formData.get('valor')).toFixed(2);

  if (new Date(data) < new Date(hoje)) {
    alert('A data não pode ser anterior a hoje.');
    return;
  }

  if (!categoria || !descricao) {
    alert('Preencha todos os campos corretamente.');
    return;
  }

  dadosParaEnviar = { data, categoria, descricao, valor };

  document.getElementById('resumoData').textContent = data;
  document.getElementById('resumoCategoria').textContent = categoria;
  document.getElementById('resumoDescricao').textContent = descricao;
  document.getElementById('resumoValor').textContent = valor;

  form.style.display = 'none';
  resumo.style.display = 'block';
});

btnEditar.addEventListener('click', () => {
  resumo.style.display = 'none';
  form.style.display = 'block';
  document.getElementById('categoria').focus();
});

btnConfirmar.addEventListener('click', async () => {
  try {
    const response = await fetch('/despesas', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(dadosParaEnviar)
    });

    if (response.ok) {
      resumo.style.display = 'none';
      message.style.display = 'block';
    } else {
      alert('Erro ao registrar despesa.');
    }
  } catch (error) {
    console.error('Erro:', error);
    alert('Erro ao conectar com o servidor.');
  }
});

btnVoltar.addEventListener('click', () => {
  message.style.display = 'none';
  form.reset();
  inputData.value = hoje;
  form.style.display = 'block';
  document.getElementById('categoria').focus();
});
