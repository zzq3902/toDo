document.getElementById('task-form').addEventListener('submit', function(e){
    // 페이지 새로고침 방지
    e.preventDefault();

    // 입력 값 가져오기
    const task = document.getElementById('task-input').value;

    // 리스트에 새 항목 추가
    const li = document.createElement('li');
    li.appendChild(document.createTextNode(task));
    const taskList = document.getElementById('task-list');
    taskList.appendChild(li);

    // 입력 필드 초기화
    document.getElementById('task-input').value = '';
});