import { get } from "./api.js";
async function getAirplaneDetailsById(airplaneId) {
    return get(`/api/airplanes/details/${airplaneId}`);
}


async function insertAirplaneDetails(airplaneId) {

    const detailsSectionElement = document.getElementById('details');

    let airplane = await getAirplaneDetailsById(airplaneId);
    
    const titleElement = document.createElement('h1');
    titleElement.textContent = airplane.model;
    titleElement.setAttribute('class', 'text-light');
    detailsSectionElement.appendChild(titleElement);

    const imgElement = document.createElement('img');
    imgElement.src = airplane.imageUrl;
    imgElement.setAttribute('height', '300px');
    imgElement.setAttribute('width', '300px');
    imgElement.alt = `Image of ${airplane.model}`;

    const tableElement = document.createElement('table');
    // tableElement.setAttribute('border', '1');
    tableElement.setAttribute('class', 'text-light');

    const tdImageElement = document.createElement('td');
    tdImageElement.appendChild(imgElement);
    // tdImageElement.setAttribute('class', 'text-center');
    tableElement.appendChild(tdImageElement);

    tableElement.appendChild(createRow(['Maximum Seats', airplane.maximumSeats]));
    tableElement.appendChild(createRow(['Length', airplane.length]));
    tableElement.appendChild(createRow(['Wingspan', airplane.wingspan]));
    tableElement.appendChild(createRow(['Height', airplane.height]));
    tableElement.appendChild(createRow(['Maximum Weight', airplane.maximumWeight]));
    tableElement.appendChild(createRow(['Maximum Cruising Speed', airplane.maximumCruisingSpeed]));
    tableElement.appendChild(createRow(['Maximum Cruising Altitude', airplane.maximumCruisingAltitude]));
    tableElement.appendChild(createRow(['Range', airplane.range]));
    tableElement.appendChild(createRow(['Engines', airplane.engines]));

    detailsSectionElement.appendChild(tableElement);

    function createRow([label, value]) {
        const trElement = document.createElement('tr');

        const tdLabelElement = document.createElement('td');
        tdLabelElement.textContent = label;
        trElement.appendChild(tdLabelElement);
        
        const tdValueElement = document.createElement('td');
        tdValueElement.textContent = value;
        trElement.appendChild(tdValueElement);

        return trElement;
    }
}

const urlParams = new URLSearchParams(window.location.search);
const airplaneId = urlParams.get('airplaneId');

insertAirplaneDetails(airplaneId);