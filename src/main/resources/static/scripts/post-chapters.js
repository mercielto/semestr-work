function getUrlParameter(name) {
    const urlParams = new URLSearchParams(window.location.search);
    return urlParams.get(name);
}

function changeUrlParameterWithPushState(param, value) {
    var currentUrl = new URL(window.location);
    currentUrl.searchParams.set(param, value);
    var stateObj = { foo: "bar" };
    history.pushState(stateObj, "", currentUrl);
}

function changeBranchName(branch) {
    const a = document.getElementById("branch-name");
    a.href = "/branch/profile/" + branch["link"];
    a.innerText = branch["name"];
}

document.addEventListener("DOMContentLoaded", function() {

    const chaptersContainer = document.getElementById('contents');

    function removeChaptersAfter(selectedLi) {
        let nextSibling = selectedLi.nextElementSibling;
        while (nextSibling) {
            let toRemove = nextSibling;
            nextSibling = nextSibling.nextElementSibling;
            chaptersContainer.removeChild(toRemove);
        }
    }

    function addChangeListenerToSelect(chapterSelect) {
        chapterSelect.addEventListener("change", function() {
            const selectedOption = chapterSelect.options[chapterSelect.selectedIndex];
            const branchLink = selectedOption.dataset.link;
            const chapterLink = selectedOption.dataset.chapterlink;
            console.log("Selected value:", chapterSelect.value, "link:", branchLink);

            const a = chapterSelect.closest("li").querySelector("a.chapter");
            a.href = '/chapter/' + chapterLink;
            const number = a.dataset.number;

            changeUrlParameterWithPushState("branch", branchLink);

            function addChapter(chapterMap) {
                for (const [key, chaptersList] of Object.entries(chapterMap)) {

                    if (key <= number) {
                        continue;
                    }

                    const selected = chaptersList[0];
                    const li = document.createElement('li');

                    const a = document.createElement('a');
                    a.className = 'chapter';
                    a.href = `/chapter/${selected.link}`;
                    a.textContent = `Глава ${key}:`;
                    a.setAttribute("data-number", key);

                    li.appendChild(a);

                    if (chaptersList.length > 1) {
                        const select = document.createElement('select');
                        select.className = 'chapter-select';

                        chaptersList.forEach(chapter => {
                            const option = document.createElement('option');
                            option.setAttribute('data-link', chapter.branchLink);
                            option.setAttribute('data-chapterlink', chapter.link);
                            option.textContent = chapter.title;
                            select.appendChild(option);
                        });

                        li.appendChild(select);
                        addChangeListenerToSelect(select);
                    } else {
                        const span = document.createElement('span');
                        span.textContent = " " + selected.title;
                        li.appendChild(span);
                    }

                    chaptersContainer.appendChild(li);
                }
            }

            $.ajax({
                url: "/branch/ajax/content/" + branchLink,
                type: "get",
                contentType: "application/json",
                success: function(response) {
                    console.log(response);
                    const parentLi = chapterSelect.closest("li");
                    removeChaptersAfter(parentLi);
                    addChapter(response["chapters"]);
                    changeBranchName(response["branch"]);
                },
                error: function(xhr, status, error) {
                    console.error('There was a problem:', error);
                }
            });
        });
    }

    const chapterSelects = document.querySelectorAll(".chapter-select");
    chapterSelects.forEach(addChangeListenerToSelect); // Добавляем слушатель к уже существующим select

    const chapterNumberInput = document.getElementById("chapter-number-input");
    const createBranchButton = document.getElementById("create-branch-button");

    createBranchButton.addEventListener("click", function() {
        window.location.href = "/branch/create/profile?branchLink="
            + getUrlParameter("branch") + "&number=" + chapterNumberInput.value;
    });
    chapterNumberInput.addEventListener("input", function() {
        const value = parseInt(this.value);
        const maxChapterNumber = document.querySelectorAll(".contents .chapter").length + 1;
        if (value < 1 || value > maxChapterNumber) {
            this.value = "";
        }
    });
});
