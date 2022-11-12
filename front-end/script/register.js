import config from "../config.json" assert { type: "json" };

const [form] = document.forms;
const toEdit = {
  id: null,
};

const fillToEdit = async (carId) => {
  const response = await fetch(`${config.endpoint}/${carId}`, {
    headers: {
      Authorization: `Basic ${config.token}`,
    },
  });

  if (response.ok) {
    const json = await response.json();
    const data = { ...json, ...json.environment, ...json.environment.location };
    delete data.id;
    delete data.environment;
    delete data.location;
    delete data.regional;

    Object.keys(data).forEach((key) => (form[key].value = data[key]));
  } else throw `Erro ao cadastrar o veículo!`;
};

const createCar = async (body) => {
  const response = await fetch(config.endpoint, {
    method: "post",
    headers: {
      Authorization: `Basic ${config.token}`,
      "Content-Type": "application/json",
    },
    body,
  });

  if (response.ok) {
    const json = await response.json();
    return json;
  }
  throw `Erro ao cadastrar o veículo!\n${body}`;
};

const editCar = async (body, carId) => {
  const response = await fetch(`${config.endpoint}/${carId}`, {
    method: "put",
    headers: {
      Authorization: `Basic ${config.token}`,
      "Content-Type": "application/json",
    },
    body,
  });

  if (response.ok) {
    const json = await response.json();
    return json;
  }
  throw `Erro ao cadastrar o veículo!\n${body}`;
};

const handleSubmit = async (event) => {
  event.preventDefault();
  const formData = new FormData(event.target);
  const jsonData = Object.fromEntries(formData);
  console.log(jsonData);

  const payload = {
    brand: jsonData.brand,
    model: jsonData.model,
    plate: jsonData.plate,
    km: jsonData.km,
    environment: {
      temperature: jsonData.temperature,
      airQuality: jsonData.airQuality,
      location: {
        district: jsonData.district,
        city: jsonData.city,
        state: jsonData.state,
      },
    },
  };

  if (toEdit.id) {
    const car = await editCar(JSON.stringify(payload, null, 2), toEdit.id);
    console.log(`Veículo editado!\n`, car);
  } else {
    const car = await createCar(JSON.stringify(payload, null, 2));
    console.log(`Veículo criado!\n`, car);
  }

  form.reset();
  window.location.assign("index.html");
};

form.addEventListener("submit", handleSubmit);

const params = new URLSearchParams(window.location.search);
const carId = params.get("edit");
if (carId) {
  document.title = "Editar veículo";
  toEdit.id = carId;
  fillToEdit(carId);
}
