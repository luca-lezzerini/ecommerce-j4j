import { Prodotto } from './prodotto';
import { LoginResponseDto } from './login-response-dto';

export class ProdottoCreateDto extends LoginResponseDto{
  dati: Prodotto;
}
