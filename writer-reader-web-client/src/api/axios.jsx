import axios from "axios";

export default axios.create({
  baseURL: "http://localhost:80",
  withCredentials: true,
  withXSRFToken: true,
  headers: {
    "Content-Type": "application/json",
    Accept: "application/json",
  },
});
