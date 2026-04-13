let shoppingBag = document.querySelector('#cart');
let body = document.querySelector('body');

shoppingBag.addEventListener('click', ()=> {
    body.classList.toggle('showCart')
})
closeCart.addEventListener('click', ()=>{
    body.classList.toggle('showCart')
})