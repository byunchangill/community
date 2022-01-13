{
    let idChkState = 2; // 0: 아이디 사용 불가능, 1: 아이디 사용 가능, 2: 체크 안함.
    const joinFrmElem = document.querySelector('#join-frm');
    const idChkMsgElem = joinFrmElem.querySelector('#id-Chk-msg');
    const idRegex = /^([a-zA-Z0-9]{4,15})$/; // 대소문자 + 숫자 조합으로 4~15 글자만 가능.
    const pwRegex = /^([a-zA-Z0-9!@_]{4,20})$/; // 대소문자 + 숫자 !@_ 조합으로 4~20 글자만 가능.
    const nmRegex = /^([가-힣]{2,6})$/;
    const msg1 = '아이디는 대소문자, 숫자 조합으로 4~15글자가 되어야 합니다.';

    const setIdChkMsg = (data) => {
        idChkState = data.result; // 0 or 1.

        switch (data.result) {
            case 0:
                idChkMsgElem.innerHTML = '이미 사용중인 아이디 입니다.';
                break;
            case 1:
                idChkMsgElem.innerHTML = '사용 가능한 아이디 입니다.';
                break;
        }
    }

    if(joinFrmElem) {
        joinFrmElem.addEventListener('submit', (e) => {
            const uid = joinFrmElem.uid.value;
            const upw = joinFrmElem.upw.value;
            const nm = joinFrmElem.nm.value;
            if(!idRegex.test(uid)) {
                alert(msg1);
                e.preventDefault();
            } else if(!pwRegex.test(upw)) {
                alert('비밀번호는 대소문자, 숫자, !,@,_ 조합으로 4~20글자가 되어야 합니다.');
                e.preventDefault();
            } else if(!nmRegex.test(nm)) {
                alert('이름은 한글로 2~6글자가 되어야 합니다.');
                e.preventDefault();
            }
            if(idChkState !== 1) {
                switch (idChkState) {
                    case 0:
                        alert('다른 아이디를 사용해주세요.');
                        break;
                    case 2:
                        alert('아이디 중복체크를 해주세요.');
                        break;
                }
                e.preventDefault();
            }
        });

        joinFrmElem.uid.addEventListener('change', () => { // 중복체크후 아이디 수정시 충복체크 버튼 해제.
            idChkMsgElem.innerHTML = '';
            idChkState = 2;
        });

        // 아이디 중복 체크
        const idBtnChkELem = joinFrmElem.querySelector('#id-btn-chk');

        idBtnChkELem.addEventListener('click', () => {
           const idVal = joinFrmElem.uid.value;
            if(idVal.length < 4) {
                alert('아이디는 4자 이상 작성해 주세요.');
                return;
            }
            
           if(!idRegex.test(idVal)) {
               alert(msg1);
               return;
           }

           fetch(`/user/idChk/${idVal}`)
               .then(res => res.json())
               .then((data) => {
                   console.log(data);
                   setIdChkMsg(data);
               }).catch((e) => {
                    console.log(e);
           });
        });
    }
}