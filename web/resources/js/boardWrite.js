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
        axios.post('/boardWrite', param, {
            headers: {
                "Content-Type": "multipart/form-data",
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

                const formData = new FormData();

                formData.append("files", document.querySelector("input[name=file]").files[0]);
                formData.append("cate_cd", document.querySelector("select[name=cate_cd]").value);
                formData.append("title", document.querySelector("input[name=title]").value);
                formData.append("content", document.querySelector("textarea[name=content]").value);

                return formData;
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