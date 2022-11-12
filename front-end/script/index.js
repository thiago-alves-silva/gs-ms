import config from "../config.json" assert { type: "json" };

/**
 * @returns {Promise<Array>}
 */
const getCars = async () => {
  const response = await fetch(config.endpoint, {
    headers: {
      Authorization: `Basic ${config.token}`,
    },
  });
  const json = await response.json();
  return json;
};

const editCar = async ({ target }) => {
  const carId = target.dataset.id;
  window.location.assign(`/register.html?edit=${carId}`);
};

const deleteCar = async ({ target }) => {
  const carId = target.dataset.id;

  const response = await fetch(`${config.endpoint}/${carId}`, {
    method: "delete",
    headers: {
      Authorization: `Basic ${config.token}`,
    },
  });

  if (response.ok) buildTable();
};

const buildTable = async () => {
  const tBody = document.querySelector("#table-car tbody");
  const cars = await getCars();

  const tableBody = cars.reduce(
    (acc, value) =>
      acc +
      `<tr>
        <td>${value.brand}</td>
        <td>${value.model}</td>
        <td>${value.plate}</td>
        <td>${value.km}</td>
        <td>${value.environment.location.district}</td>
        <td>${value.environment.location.city}</td>
        <td>${value.environment.location.state}</td>
        <td>${value.environment.temperature}</td>
        <td>${value.environment.airQuality}</td>
        <td><button class="edit" data-id="${value.id}">Editar</button></td>
        <td><button class="delete" data-id="${value.id}">Remover</button></td>
      </tr>`,

    ""
  );
  tBody.innerHTML = tableBody;

  tBody.querySelectorAll(".edit").forEach((btn) => (btn.onclick = editCar));
  tBody.querySelectorAll(".delete").forEach((btn) => (btn.onclick = deleteCar));
};

buildTable();
