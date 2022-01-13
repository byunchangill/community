{
    // Data Element.
    const dataElem = document.querySelector('#data');

    //input type="file"
    const profileFileElem = document.querySelector('#profile-file');
    if(profileFileElem) {
        profileFileElem.addEventListener('change', ()=> {
            const img = profileFileElem.files[0];
            if(img != null) {
                uploadProfileImg(img);
            }
        });
    }

    //프로필 이미지 클릭 이벤트
    const profileViewElem = document.querySelector('#profile-view');
    if(profileViewElem) {
        profileViewElem.addEventListener('click', () => {
            if(profileFileElem) {
                profileFileElem.click();
            }
        });
    }

    //이미지 업로드
    const uploadProfileImg = (img) => {
        const fData = new FormData();
        fData.append('profileImg', img);

        fetch('/user/mypage/profile', {
            'method': 'post',
            'body': fData
        })
            .then(res => res.json())
            .then(data => {
                console.log(data);
                setProfileImg(data);
            })
            .catch(e => {
                console.log(e);
            });
    }

    //이미지 세팅
    const setProfileImg = (data) => {
        if(!data.result) { return; }

        //프로필 이미지
        const iuser = dataElem.dataset.iuser;
        const src = `/images/user/${iuser}/${data.result}`;

        const profileImgElem = profileViewElem.querySelector('img');
        profileImgElem.src = src;

        //헤더 이미지
        const headerProfileImgElem = document.querySelector('#header-profileimg');
        headerProfileImgElem.src = src;
    }
}