import { get } from "./api.js";
async function getAllAirplanes() {
    return get('/api/airplanes/all');
}


async function insertAllAirplanes() {

    const boxesElement = document.getElementById('boxes');

    let allAirplanes = await getAllAirplanes();
    
    allAirplanes.forEach(airplane => {
        const aElement = document.createElement('a');
        aElement.href = `/airplanes/details?airplaneId=${airplane.id}`;
        aElement.setAttribute('class', 'main-btn');
        const divElement = document.createElement('div');
        divElement.setAttribute('class', 'box');
    
        const imgElement = document.createElement('img');
        imgElement.src = airplane.imageUrl;
        imgElement.setAttribute('height', '150px');
        imgElement.alt = `Image of ${airplane.model}`;
        divElement.appendChild(imgElement);
    
        const h3Element = document.createElement('h3');
        h3Element.textContent = `${airplane.model}`;
        divElement.appendChild(h3Element);
    
        aElement.appendChild(divElement);
        boxesElement.appendChild(aElement);
    });
    
}

insertAllAirplanes();