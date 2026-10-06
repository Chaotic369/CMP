chrome.action.onClicked.addListener(async (tab) => {
  if (chrome.sidePanel?.open) {
    await chrome.sidePanel.open({ windowId: tab.windowId });
  } else {
    chrome.windows.create({ url: chrome.runtime.getURL("index.html"), type: "popup", width: 780, height: 960 });
  }
});
chrome.runtime.onMessage.addListener((message, sender, sendResponse) => {
  if (message.action === "toggle_fullscreen" && sender.tab?.windowId) {
    chrome.windows.get(sender.tab.windowId, (win) => {
      chrome.windows.update(win.id, { state: win.state === "fullscreen" ? "normal" : "fullscreen" });
    });
  }
});