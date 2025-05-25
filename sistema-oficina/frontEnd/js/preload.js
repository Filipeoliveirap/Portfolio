const { contextBridge, ipcRenderer } = require('electron');

// Expondo a função "sairDoApp" para o frontend através do contextBridge
contextBridge.exposeInMainWorld('electron', {
  sairDoApp: () => {
    // Envia a mensagem 'sair-do-app' para o main process
    ipcRenderer.send('sair-do-app');
  }
});
