export function isValidCPF(value: string) {
  const cpf = value.replace(/\D/g, "");
  return cpf.length === 11;
}

export function isValidEmail(value: string) {
  return /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(value);
}

export function isValidPhone(value: string) {
  const phone = value.replace(/\D/g, "");
  return phone.length >= 10 && phone.length <= 11;
}

export function isValidDate(value: string) {
  const date = value.replace(/\D/g, "");
  return date.length === 8;
}

export function isValidSenha(value: string) {
    return value.length >= 5;
}