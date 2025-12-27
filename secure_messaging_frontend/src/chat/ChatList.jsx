function ChatList({ setSelectedChat }) {
  const chats = ["user1", "user2", "user3"];

  return (
    <div style={{ width: "30%", borderRight: "1px solid gray", padding: "10px" }}>
      <h3>Chats</h3>

      {chats.map((chat) => (
        <div
          key={chat}
          style={{ padding: "8px", cursor: "pointer" }}
          onClick={() => setSelectedChat(chat)}
        >
          {chat}
        </div>
      ))}
    </div>
  );
}

export default ChatList;
