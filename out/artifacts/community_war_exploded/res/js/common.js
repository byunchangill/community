const msg = {
    'isDel': '삭제하시겠습니까?',
    'fnIsDel': function(target) {
        return `${target}을(를)` +this.isDel;
    }
}

const regex = {
    id: /^([a-zA-Z0-9]{4,15})$/, //대소문자 + 숫자 조합으로 4~15 글자만 가능.
    pw: /^([a-zA-Z0-9!@_]{4,20})$/, //대소문자 + 숫자 !@_ 조합으로 4~20 글자만 가능.
    nm: /^([가-힣]{2,6})$/, // 한글 조합으로 2~6글자.
    ctnt: /^[^><]*$/,
    mag: {
        id: '대소문자 + 숫자 조합으로 4~15 글자만 가능.',
        pw: '대소문자 + 숫자 !@_ 조합으로 4~20 글자만 가능.',
        nm: '한글조합으로 2~6글자',
        ctnt: '<, > 는 사용할 수 없습니다.',
    },
    isWrongWith: function (target, val) {
        return (target && val) ? !this[target].test(val) : true;
    }
};

const myFetch = {
    send: function (fetchObj, cb) {
        return fetchObj
            .then(res => res.json())
            .then(cb)
            .catch(e => {
                console.log(e);
            })
    },
    get: function (url, param, cb) {
        if (param) {
            const queryString = Object.keys(param).map(key => `${key}=${param[key]}`).join('&');
            url = `${url}?${queryString}`;
        }
        return this.send(fetch(url, cb));
    },
    post: function (url, param, cb) {
        return this.send(fetch(url, {
            'method': 'post',
            'headers': { 'Content-Type': 'application/json' },
            'body': JSON.stringify(param)
        }), cb);
    }
}