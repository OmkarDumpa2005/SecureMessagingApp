import { useState } from "react";
import ChatList from "../chat/ChatList";
import ChatWindow from "../chat/ChatWindow";

function Dashboard() {
  const [selectedChat, setSelectedChat] = useState(null);

  return (
    <div style={{ display: "flex", height: "100vh" }}>
      <ChatList setSelectedChat={setSelectedChat} />
      <ChatWindow selectedChat={selectedChat} />
    </div>
  );
}

export default Dashboard;
