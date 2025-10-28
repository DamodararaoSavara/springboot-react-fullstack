import React, { useEffect, useState } from "react";
import PlayerService from "../services/PlayerService";
import "./PlayerList.css";

const PlayerList = () => {
  const [players, setPlayers] = useState([]);
  const [newPlayer, setNewPlayer] = useState({
    playerName: "",
    playerCountry: "",
    playerRole: "",
  });
  const [editMode, setEditMode] = useState(false);
  const [editingPlayerId, setEditingPlayerId] = useState(null);
  // For delete confirmation modal
  const [showConfirm, setShowConfirm] = useState(false);
  const [playerToDelete, setPlayerToDelete] = useState(null);

  useEffect(() => {
    loadPlayers();
  }, []);

  const loadPlayers = () => {
    PlayerService.getAllPlayers().then((response) => {
      setPlayers(response.data);
    });
  };
  const handleAddPlayer = (e) => {
    e.preventDefault();
    if (editMode) {
      PlayerService.updatePlayer(editingPlayerId, newPlayer).then(() => {
        setNewPlayer({ playerName: "", playerCountry: "", playerRole: "" });
        setEditMode(false);
        setEditingPlayerId(null);
        loadPlayers();
      });
    } else {
      PlayerService.addPlayer(newPlayer).then(() => {
        setNewPlayer({ playerName: "", playerCountry: "", playerRole: "" });
        loadPlayers();
      });
    }
  };
  //   const handleDeletePlayer = (id) => {
  //     const confirmDelete = window.confirm(
  //       "Are you sure you want to delete this player?"
  //     );
  //     if (confirmDelete) {
  //       PlayerService.deletePlayer(id).then(() => {
  //         loadPlayers();
  //       });
  //     }
  //   };
  const handleDeletePlayer = (player) => {
    setPlayerToDelete(player);
    setShowConfirm(true);
  };

  const confirmDelete = () => {
    if (playerToDelete) {
      PlayerService.deletePlayer(playerToDelete.id).then(() => {
        loadPlayers();
        setShowConfirm(false);
        setPlayerToDelete(null);
      });
    }
  };
  const handleEditPlayer = (player) => {
    setNewPlayer({
      playerName: player.playerName,
      playerCountry: player.playerCountry,
      playerRole: player.playerRole,
    });
    setEditingPlayerId(player.id);
    setEditMode(true);
    window.scrollTo({ top: 0, behavior: "smooth" });
  };
  return (
    <>
      <div className="main-container">
        <h1 className="main-title">🏏 Cricket Player Management</h1>
        {/* Add New Player Form */}
        <div className="form-section">
          <h2>Add New Player</h2>
          <form onSubmit={handleAddPlayer} className="player-form">
            <input
              type="text"
              placeholder="Name"
              value={newPlayer.playerName}
              onChange={(e) =>
                setNewPlayer({ ...newPlayer, playerName: e.target.value })
              }
              required
            />
            <input
              type="text"
              placeholder="Country"
              value={newPlayer.playerCountry}
              onChange={(e) =>
                setNewPlayer({ ...newPlayer, playerCountry: e.target.value })
              }
              required
            />
            <input
              type="text"
              placeholder="Role"
              value={newPlayer.playerRole}
              onChange={(e) =>
                setNewPlayer({ ...newPlayer, playerRole: e.target.value })
              }
              required
            />
            {
              <button type="submit">
                {editMode ? "💾 Update Player" : "➕ Add Player"}
              </button>
            }
            {editMode && (
              <button
                type="button"
                onClick={() => {
                  setEditMode(false);
                  setNewPlayer({
                    playerName: "",
                    playerCountry: "",
                    playerRole: "",
                  });
                }}
              >
                ❌ Cancel
              </button>
            )}
          </form>
        </div>
        {/* Player List */}
        <div className="player-container">
          <h2>Team Players</h2>
          <div className="player-grid">
            {players.length > 0 ? (
              players.map((player) => (
                <div className="player-card" key={player.id}>
                  <h2>{player.playerName}</h2>
                  <p>
                    <strong>Country:</strong> {player.playerCountry}
                  </p>
                  <p>
                    <strong>Role:</strong> {player.playerRole}
                  </p>
                  <div className="btn-group">
                    <button
                      className="edit-btn"
                      onClick={() => handleEditPlayer(player)}
                    >
                      ✏️ Edit
                    </button>
                    <button
                      className="delete-btn"
                      onClick={() => handleDeletePlayer(player.id)}
                    >
                      🗑️ Delete
                    </button>
                  </div>
                </div>
              ))
            ) : (
              <p className="no-data">No players found. Add some!</p>
            )}
          </div>
        </div>
      </div>
      {/* Confirmation Modal */}
      {showConfirm && (
        <div className="modal-overlay">
          <div className="modal">
            <h3>Are you sure?</h3>
            <p>
              Do you really want to delete{" "}
              <strong>{playerToDelete?.playerName}</strong>?
            </p>
            <div className="modal-buttons">
              <button className="confirm-btn" onClick={confirmDelete}>
                ✅ Yes, Delete
              </button>
              <button
                className="cancel-btn"
                onClick={() => setShowConfirm(false)}
              >
                ❌ Cancel
              </button>
            </div>
          </div>
        </div>
      )}
    </>
  );
};

export default PlayerList;
