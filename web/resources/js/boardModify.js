$(function () {

    document.querySelector("#sub-btn").addEventListener("click", function (e) {

        e.preventDefault();

        let chkResult = func.set.chkValue();
        console.log(chkResult);

        if (!chkResult.result) {
            alert(chkResult.element.dataset.title + "을 입력해 주세요");
            chkResult.element.focus();
            return;
        }

        let param = func.get.dataInfo();
        console.log(param);

        axios.post('/boardModify', param, {
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            }
        }).then((res) => {
            if (res.data) {
                // TODO: 여기 해당 게시판 리스트로 보내는거 해야함.
                location.href = "/";
            }
        }).catch((error) => {
            console.log(error);
        });

    })

    let func = {
        get: {
            dataInfo: function () {
                return {
                    b_cd: document.querySelector("input[name=b_no]").value,
                    cate_cd: document.querySelector("select[name=cate_cd]").value,
                    title: document.querySelector("input[name=title]").value,
                    content: document.querySelector("textarea[name=content]").value
                };
            }
        },
        set: {
            chkValue: function () {

                let result = {
                    result: true,
                    element: ""
                };

                const requireds = document.querySelectorAll(".required");

                for (let require of requireds) {

                    if (require.value === "") {
                        result.result = false;
                        result.element = require;
                        return result;
                    }
                }

                return result;
            }
        }
    }

});