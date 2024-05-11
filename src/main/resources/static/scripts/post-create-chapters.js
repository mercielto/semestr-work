function replaceBody(data) {
    const mainBody = document.getElementById("main-body");

    while (mainBody.firstChild) {
        mainBody.removeChild(mainBody.firstChild);
    }

    mainBody.insertAdjacentHTML("beforeend", data);
}


$(document).ready(function() {
    $(".chapter-add").click(function(){
        window.location.href = updateQueryStringParameter(window.location.href, "part", "chapter-add");
    });
});

const chapterDividers = document.querySelectorAll('.chapter-divider');

chapterDividers.forEach(divider => {
    const addButton = divider.querySelector('.chapter-add');

    divider.addEventListener('mouseenter', function() {
        addButton.style.display = 'inline-block';
    });

    divider.addEventListener('mouseleave', function() {
        addButton.style.display = 'none';
    });
});

const deleteButtons = document.querySelectorAll('.delete-chapter-btn');

deleteButtons.forEach(button => {
    button.addEventListener('click', function() {
        const chapter = button.closest('.chapter');
        const confirmDelete = window.confirm('Вы уверены, что хотите удалить эту главу?');

        if (confirmDelete) {
            const chapterNameText = chapter.querySelector('.chapter-name p')
                .textContent.split(".");
            var data = {
                "chapterNumber": parseInt(chapterNameText[0])
            };

            console.log(data);

            $.ajax({
                url: "/post/ajax/chapter/" + getLink(),
                type: "delete",
                data: JSON.stringify(data),
                contentType: "application/json",
                success: function(data) {
                    console.log("Success");
                    replaceBody(data);
                },
                error: function(xhr, status, error) {
                    console.error("Error:", error);
                }
            });


        }
    });
});
