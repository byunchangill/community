{
    const dataElem = document.querySelector('#data');

    const btnDelElem = document.querySelector('#btnDel');
    if(btnDelElem) {
        btnDelElem.addEventListener('click', () => {
            const icategory = dataElem.dataset.icategory;
            const iboard = dataElem.dataset.iboard;
            if (confirm(msg.fnIsDel(`${iboard}번 글`))) {
                location.href = `/board/del?icategory=${icategory}&iboard=${iboard}`;
            }
        });
    }

    // 수정 버튼.
    const modBtnElem = document.querySelector('#btnMod');
    if(modBtnElem) {
        modBtnElem.addEventListener('click', ()=> {
            const iboard = dataElem.dataset.iboard;
            location.href=`/board/mod?iboard=${iboard}`;
        });
    }

    const cmtFrmElem = document.querySelector('#cmtFrm')
    if(cmtFrmElem) { // true: 로그인 한 상태.

        //input-text ctnt에서 엔터치면 submit날아가기 때문에 막는다.
        cmtFrmElem.addEventListener('submit', (e)=> {
            e.preventDefault();
        });

        cmtFrmElem.btn_submit.addEventListener('click', () => {
            const cmtVal = cmtFrmElem.ctnt.value;
            if(cmtVal.length === 0) {
                alert('댓글 내용을 작성해 주세요.');
            } else if (regex.isWrongWith('ctnt', cmtVal)) {
                alert(regex.mag.ctnt);
            } else {
                insBoardCmtAjax(cmtVal);
            }
        });

        // 댓글 작성.
        const insBoardCmtAjax = (val) => {
            const param = {
                'iboard': dataElem.dataset.iboard,
                'ctnt': val
            };
            myFetch.post('/board/cmt', (data) => {
                console.log(data);
            },param);
        }
    }
}
