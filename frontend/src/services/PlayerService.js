import axios from "axios";
const BASE_URL = "http://localhost:8080/api/player";

class PlayerService {
  getAllPlayers() {
    return axios.get(BASE_URL);
  }
  addPlayer(player) {
    return axios.post(BASE_URL, player);
  }
  updatePlayer(editingPlayerId, player) {
    return axios.put(`${BASE_URL}/${editingPlayerId}`, player);
  }
  deletePlayer(id) {
    return axios.delete(`${BASE_URL}/${id}`);
  }
}
export default new PlayerService();
