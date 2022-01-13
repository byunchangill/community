{
    const psFrmElem = document.querySelector('#ps-frm');
    if(psFrmElem) {
        psFrmElem.addEventListener('submit', (e) => {
            const currentUpwVal = psFrmElem.currentUpw.value;
            const upwVal = psFrmElem.upw.value;
            const confirmUpwVal = psFrmElem.confirmUpw.value;

            if(currentUpwVal.length === 0) {
                alert('현재 비밀번호를 작성해 주세요.');
                e.preventDefault();
            } else if(upwVal.length === 0) {
                alert('새 비밀번호를 작성해 주세요.');
                e.preventDefault();
            } else if(upwVal !== confirmUpwVal) {
                alert('새 비밀번호와 새 비밀번호 확인 이 다릅니다.');
                e.preventDefault();
            } else if(regex.isWrongWith('pw',currentUpwVal)) {
                alert(`현재 비밀번호가 ${regex.msg.pw}인지 확인해 주세요.`);
                e.preventDefault();
            } else if(regex.isWrongWith('pw',upwVal)) {
                alert(`새 비밀번호가 ${regex.msg.pw}인지 확인해 주세요.`);
                e.preventDefault();
            }
        });
    }
}