import { Prodotto } from './prodotto';
import { LoginResponseDto } from './login-response-dto';

export class ProdottiCreateDto extends LoginResponseDto{
  dati: Prodotto;
}
