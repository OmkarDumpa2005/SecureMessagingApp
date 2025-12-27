function ChatWindow({ selectedChat }) {
  if (!selectedChat) {
    return (
      <div style={{ width: "70%", padding: "10px" }}>
        <p>Select a chat to start messaging</p>
      </div>
    );
  }

  return (
    <div style={{ width: "70%", padding: "10px" }}>
      <h3>Chat with {selectedChat}</h3>

      <div style={{ marginTop: "20px" }}>
        <p>Hello 👋</p>
        <p>How are you?</p>
      </div>
    </div>
  );
}

export default ChatWindow;
