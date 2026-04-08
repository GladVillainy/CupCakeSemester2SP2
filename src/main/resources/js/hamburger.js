Menu.style.display = "none";

function Show(x) {
    x.classList.toggle("change");
    var Menu = document.getElementById("hamburgerMenu");
    if (Menu.style.display === "block") {
        Menu.style.display = "none";
    } else {
        Menu.style.display = "block";
    }
}