let categories = ["Trabalho", "Estudos", "Pessoal"];

function openModal() {
    const m = document.getElementById('taskModal');
    const catSelect = document.getElementById('mCategory');
    catSelect.innerHTML = categories.map(c => `<option>${c}</option>`).join('');
    document.getElementById('modalTitle').innerText = "Nova Tarefa";
    m.style.display = 'flex';
}

function closeModal() {
    document.getElementById('taskModal').style.display = 'none';
}
