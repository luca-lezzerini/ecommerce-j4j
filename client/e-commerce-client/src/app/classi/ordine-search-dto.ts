import { LoginResponseDto } from './login-response-dto';

export class OrdineSearchDto extends LoginResponseDto {
  stato: string;
  searchKeyData: Date;
  searchKeyNumeroOrdine: number;
}
