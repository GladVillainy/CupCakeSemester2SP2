let shoppingBag = document.querySelector('#cart');
let body = document.querySelector('body');
let closeCart = document.querySelector('.close')

shoppingBag.addEventListener('click', ()=> {
    body.classList.toggle('showCart')
})
closeCart.addEventListener('click', ()=>{
    body.classList.toggle('showCart')
})