const isText = async (file) => {
  const reader = new FileReader()
  let b = false
  try {
    await new Promise((resolve, reject) => {
      reader.onload = () => resolve()
      reader.onerror = () => reject(reader.error)
      reader.readAsArrayBuffer(file)
    })
    const decoder = new TextDecoder('utf-8')
    const text = decoder.decode(reader.result)
    b = /^[\x00-\x7F]*$/.test(text)
  } catch (e) {}
  return b
}
