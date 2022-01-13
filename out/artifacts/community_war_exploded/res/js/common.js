const msg = {
    'isDel': '삭제하시겠습니까?',
    'fnIsDel': function(target) {
        return `${target}을(를)` +this.isDel;
    }
}

const regex = {
    id: /^([a-zA-Z0-9]{4,15})$/, //대소문자 + 숫자 조합으로 4~15 글자만 가능.
    pw: /^([a-zA-Z0-9!@_]{4,20})$/, //대소문자 + 숫자 !@_ 조합으로 4~20 글자만 가능.
    nm: /^([가-힣]{2,6})$/,
    mag: {
        id: '대소문자 + 숫자 조합으로 4~15 글자만 가능.',
        pw: '대소문자 + 숫자 !@_ 조합으로 4~20 글자만 가능.',
        nm: '한글조합으로 2~6글자',
    },
    isWrongWith: function (target, val) {
        return !this[target].test(val);
    }
};